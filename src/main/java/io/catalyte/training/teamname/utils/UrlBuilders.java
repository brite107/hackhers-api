package io.catalyte.training.teamname.utils;

public class UrlBuilders {

  public static final String DEFAULT_PAGING = "&pageSize=20&pageNumber=0";

  public static String buildProductUrl(String baseUrl, String demographic, String category,
      String type, String pageSize, String pageNumber) {
    if (!demographic.equals("Men") && !demographic.equals("Women") && !demographic.equals("Kids")) {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    sb.append(baseUrl);
    sb.append("?demographic=");
    sb.append(demographic);

    if (category != null && category != "") {
      sb.append("&category=");
      sb.append(category);
    }

    if (type != null && type != "") {
      sb.append("&type=");
      sb.append(type);
    }

    if (pageSize == null || pageNumber == null) {
      sb.append(DEFAULT_PAGING);
    } else {
      sb.append("&pageSize=");
      sb.append(pageSize);
      sb.append("&pageNumber=");
      sb.append(pageNumber);
    }

    return sb.toString();

  }

  public static String buildImageUrl(String baseUrl, String demographic, String category,
      String type) {
    if (!demographic.equals("Men") && !demographic.equals("Women") && !demographic.equals("Kids")) {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    sb.append(baseUrl);
    sb.append("?demographic=");
    sb.append(demographic);

    if (category != null && category != "") {
      sb.append("&category=");
      sb.append(category);
    }

    if (type != null && type != "") {
      sb.append("&type=");
      sb.append(type);
    }

    return sb.toString();

  }
  public static String buildImageUrl(String baseUrl, String demographic) {
    if (!demographic.equals("Men") && !demographic.equals("Women") && !demographic.equals("Kids")) {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    sb.append(baseUrl);
    sb.append("?demographic=");
    sb.append(demographic);


    return sb.toString();

  }

}
