package io.catalyte.training.teamname.domains.product;

import java.util.List;

public interface ProductService {

  List<Product> getProductsByQuery(String demographic,

      String category,

      String type,

      String pageSize,

      String pageNumber) throws Exception;

}
