package io.catalyte.training.teamname.domains.image;

import static io.catalyte.training.teamname.constants.StringConstants.IMAGE_API_BASEURL;
import static io.catalyte.training.teamname.constants.StringConstants.IMAGES_ENDPOINT;

import io.catalyte.training.teamname.exceptions.ServiceUnavailable;
import io.catalyte.training.teamname.utils.UrlBuilders;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * Handles the logic for retrieving images by query from the image search service
 */
@Service
public class ImageServiceImpl implements ImageService {

  @Autowired
  private WebClient.Builder webClientBuilder;

  /**
   * @param demographic - the demographic of the image
   * @param category    - the category of the image
   * @param type        - the type of the image
   * @return - a list of images that match the query
   * @throws Exception WebClientResponseException
   */
  @Override
  public List<Image> getImagesByQuery(String demographic, String category, String type)
      throws Exception {
    StringBuilder sb = new StringBuilder();
    sb.append(IMAGE_API_BASEURL);
    sb.append(IMAGES_ENDPOINT);
    String baseUrl = sb.toString();
    String path = UrlBuilders.buildImageUrl(baseUrl, demographic, category, type);

    try {
      List<Image> images = webClientBuilder.build()
          .get()
          .uri(path)
          .retrieve()
          .bodyToMono(new ParameterizedTypeReference<List<Image>>() {
          })
          .block();
      return images;
    } catch (WebClientResponseException e) {
      throw new ServiceUnavailable(e.getMessage());
    }

  }

  public List<Image> getImagesByQuery(String demographic)
      throws Exception {
    StringBuilder sb = new StringBuilder();
    sb.append(IMAGE_API_BASEURL);
    sb.append(IMAGES_ENDPOINT);
    String baseUrl = sb.toString();
    String path = UrlBuilders.buildImageUrl(baseUrl, demographic);

    try {
      List<Image> images = webClientBuilder.build()
          .get()
          .uri(path)
          .retrieve()
          .bodyToMono(new ParameterizedTypeReference<List<Image>>() {
          })
          .block();
      return images;
    } catch (WebClientResponseException e) {
      throw new ServiceUnavailable(e.getMessage());
    }

  }
}
