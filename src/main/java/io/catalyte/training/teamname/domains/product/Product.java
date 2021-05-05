package io.catalyte.training.teamname.domains.product;

import java.math.BigDecimal;

/**
 * Class which represents an object retrieved from the product search service
 */
public class Product {

  private Long id;
  private BigDecimal price;
  private String name;
  private String description;
  private String demographic;
  private String category;
  private String type;
  private String imageUrl;
  private String releaseDate;
  private String primaryColorCode;
  private String secondaryColorCode;
  private String styleNumber;
  private String globalProductCode;

  public Product() {
  }

  public Product(Long id, BigDecimal price, String name, String description,
      String demographic, String category, String type, String imageUrl, String releaseDate,
      String primaryColorCode, String secondaryColorCode, String styleNumber,
      String globalProductCode) {
    this.id = id;
    this.price = price;
    this.name = name;
    this.description = description;
    this.demographic = demographic;
    this.category = category;
    this.type = type;
    this.imageUrl = imageUrl;
    this.releaseDate = releaseDate;
    this.primaryColorCode = primaryColorCode;
    this.secondaryColorCode = secondaryColorCode;
    this.styleNumber = styleNumber;
    this.globalProductCode = globalProductCode;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDemographic() {
    return demographic;
  }

  public void setDemographic(String demographic) {
    this.demographic = demographic;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public String getPrimaryColorCode() {
    return primaryColorCode;
  }

  public void setPrimaryColorCode(String primaryColorCode) {
    this.primaryColorCode = primaryColorCode;
  }

  public String getSecondaryColorCode() {
    return secondaryColorCode;
  }

  public void setSecondaryColorCode(String secondaryColorCode) {
    this.secondaryColorCode = secondaryColorCode;
  }

  public String getStyleNumber() {
    return styleNumber;
  }

  public void setStyleNumber(String styleNumber) {
    this.styleNumber = styleNumber;
  }

  public String getGlobalProductCode() {
    return globalProductCode;
  }

  public void setGlobalProductCode(String globalProductCode) {
    this.globalProductCode = globalProductCode;
  }
}
