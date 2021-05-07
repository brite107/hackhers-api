package io.catalyte.training.teamname.domains.image;

import java.util.List;

public interface ImageService {

  List<Image> getImagesByQuery(String demographic,

      String category,

      String type) throws Exception;

  List<Image> getImagesByQuery(String demographic) throws Exception;

}
