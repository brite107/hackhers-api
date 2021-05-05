package io.catalyte.training.teamname.domains.product;

import static io.catalyte.training.teamname.constants.StringConstants.DEFAULT_IMAGE;
import static io.catalyte.training.teamname.constants.StringConstants.PRODUCT_API;

import io.catalyte.training.teamname.domains.image.Image;
import io.catalyte.training.teamname.domains.image.ImageService;
import io.catalyte.training.teamname.exceptions.ServiceUnavailable;
import io.catalyte.training.teamname.utils.UrlBuilders;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private WebClient.Builder webClientBuilder;

  @Autowired
  private ImageService imageService;

  @Override
  public List<Product> getProductsByQuery(String demographic, String category, String type,
      String pageSize, String pageNumber) throws Exception {

    String path = UrlBuilders.buildProductUrl(PRODUCT_API, demographic, category, type, pageSize, pageNumber);
    List<Product> products;

    try {
      // get the products from the product search service
      // An object is returned from the product search service which contains the list
      // of products as one of its properties (content)
      ProductSearchServiceObject productSearchServiceObject = webClientBuilder.build()
          .get()
          .uri(path)
          .retrieve()
          .bodyToMono(ProductSearchServiceObject.class)
          .block();
      products = productSearchServiceObject.getContent();  // extract out the actual product list

      // get a list of images that match the same criteria (currently we only have one image for each
      // demographic/type pairing)
      List<Image> images = imageService
          .getImagesByQuery(demographic, category, type);

      // if the list is empty (no images matching the criteria), return all the products with default images.
      if (images.size() == 0) {
        for (int i = 0; i < products.size(); i++) {
          products.get(i).setImageUrl(DEFAULT_IMAGE);
        }
      } else if (images.size() == 1) {
        // if there's only one image in the list, return all the products with that image
        // this will be the case when we have demographic-category or demographic-type queries
        // since we only have one image per each of these.
        for (int i = 0; i < products.size(); i++) {
          products.get(i).setImageUrl(images.get(0).getImageUrl());
        }
      } else {
        // this will be when we query by demographic only, as there are 29 images per demographic
        // outer loop is for each product
        for (int i = 0; i < products.size(); i++) {
          // match an image to a product based on whether the type or category matches
          // inner loop goes through the images looking for a match in type or category
          for (int j = 0; j < images.size(); j++) {
            if (images.get(j).getType() != null) {
              if (products.get(i).getType().equals(images.get(j).getType())) {
                products.get(i).setImageUrl(images.get(j).getImageUrl());
                break;
              }
            } else if (images.get(j).getCategory() != null) {
              if (products.get(i).getCategory().equals(images.get(j).getCategory())) {
                products.get(i).setImageUrl(images.get(j).getImageUrl());
                break;
              }
            }
          }
        }
      }

      return products;
    } catch (
        WebClientResponseException e) {
      throw new ServiceUnavailable(e.getMessage());
    }

  }
}
