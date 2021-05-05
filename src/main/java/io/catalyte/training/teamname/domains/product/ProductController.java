package io.catalyte.training.teamname.domains.product;

import static io.catalyte.training.teamname.constants.StringConstants.CONTEXT_PRODUCTS;

import io.catalyte.training.teamname.exceptions.ServiceUnavailable;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Holds GET method to query product search service
 */
@RestController
@RequestMapping(CONTEXT_PRODUCTS)
@ApiResponses(value = {
    @ApiResponse(code = 500, message = "Internal Server Error", response = ServiceUnavailable.class),
    @ApiResponse(code = 503, message = "Service Unavailable", response = ServiceUnavailable.class)
})
public class ProductController {

  @Autowired
  private ProductService productService;

  /**
   * Calls the service to get all products matching criteria if a query is provided
   *
   * @param demographic - the demographic of the product
   * @param category    - the category of the product
   * @param type        - the type of the product
   * @param pageSize    - the pageSize (number of products to return)
   * @param pageNumber  - the starting number of the products to be returned
   * @return ResponseEntity<List < Product>> list of products matching criteria
   * @throws Exception ServiceUnavailable
   */
  @GetMapping()
  public ResponseEntity<List<Product>> getProductsByQuery(@RequestParam String demographic,
      @RequestParam(required = false) String category,
      @RequestParam(required = false) String type,
      @RequestParam(required = false) String pageSize,
      @RequestParam(required = false) String pageNumber) throws Exception {

    return new ResponseEntity<>(
        productService.getProductsByQuery(demographic, category, type, pageSize, pageNumber),
        HttpStatus.OK);
  }

}
