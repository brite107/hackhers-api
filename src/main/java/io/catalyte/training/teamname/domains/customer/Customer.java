package io.catalyte.training.teamname.domains.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static io.catalyte.training.teamname.constants.StringConstants.GENERATED_ID;
import static io.catalyte.training.teamname.constants.StringConstants.REQUIRED_FIELD;

/**
 * Customer entity represents a single stored Customer
 */

@Entity
@ApiModel(description = "All details about Customer")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(notes = GENERATED_ID)
  private Long id;

  @NotBlank(message = REQUIRED_FIELD)
  @Pattern(regexp = "^[A-Za-z]+$")
  @Size(min = 2, message = "First name must have at least 2 characters")
  @ApiModelProperty(notes = "Customer's first name")
  private String firstName;

  @NotBlank(message = REQUIRED_FIELD)
  @Pattern(regexp = "^[A-Za-z]+$")
  @Size(min = 2, message = "First name must have at least 2 characters")
  @ApiModelProperty(notes = "Customer's last name")
  private String lastName;


  @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
  @ApiModelProperty(notes = "Customer's password")
  private String password;

  @NotBlank(message = REQUIRED_FIELD)
  @Email
  @Column(unique = true)
  @ApiModelProperty(notes = "Customer's email")
  private String email;

  @NotBlank(message = REQUIRED_FIELD)
  @Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$")
  @ApiModelProperty(notes = "Customer's phone number")
  private String phoneNumber;

  @NotBlank(message = REQUIRED_FIELD)
  @ApiModelProperty(notes = "Customer's street")
  private String street;

  @NotBlank(message = REQUIRED_FIELD)
  @ApiModelProperty(notes = "Customer's city")
  private String city;

  @NotBlank(message = REQUIRED_FIELD)
  @Pattern(regexp = "^(([Aa][EeLlKkSsZzRr])|([Cc][AaOoTt])|([Dd][EeCc])|([Ff][MmLl])|([Gg][AaUu])|([Hh][Ii])|([Ii][DdLlNnAa])|([Kk][SsYy])|([Ll][Aa])|([Mm][EeHhDdAaIiNnSsOoTt])|([Nn][EeVvHhJjMmYyCcDd])|([Mm][Pp])|([Oo][HhKkRr])|([Pp][WwAaRr])|([Rr][Ii])|([Ss][CcDd])|([Tt][NnXx])|([Uu][Tt])|([Vv][TtIiAa])|([Ww][AaVvIiYy]))$")
  @ApiModelProperty(notes = "Customer's state")
  private String state;

  @NotBlank(message = REQUIRED_FIELD)
  @Pattern(regexp = "^(?!0{3})[0-9]{5}(?:-[0-9]{4})?$")
  @ApiModelProperty(notes = "Customer's zip code")
  private String zipCode;


  public Customer() {
  }


  public Customer(
      @NotBlank(message = REQUIRED_FIELD) @Size(min = 2, message = "First name must have at least 2 characters") String firstName,
      @NotBlank(message = REQUIRED_FIELD) @Size(min = 2, message = "First name must have at least 2 characters") String lastName,
      @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$") String password,
      @NotBlank(message = REQUIRED_FIELD) @Email String email,
      @NotBlank(message = REQUIRED_FIELD) @Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$") String phoneNumber,
      @NotBlank(message = REQUIRED_FIELD) String street,
      @NotBlank(message = REQUIRED_FIELD) String city,
      @NotBlank(message = REQUIRED_FIELD) @Pattern(regexp = "^(([Aa][EeLlKkSsZzRr])|([Cc][AaOoTt])|([Dd][EeCc])|([Ff][MmLl])|([Gg][AaUu])|([Hh][Ii])|([Ii][DdLlNnAa])|([Kk][SsYy])|([Ll][Aa])|([Mm][EeHhDdAaIiNnSsOoTt])|([Nn][EeVvHhJjMmYyCcDd])|([Mm][Pp])|([Oo][HhKkRr])|([Pp][WwAaRr])|([Rr][Ii])|([Ss][CcDd])|([Tt][NnXx])|([Uu][Tt])|([Vv][TtIiAa])|([Ww][AaVvIiYy]))$") String state,
      @NotBlank(message = REQUIRED_FIELD) @Pattern(regexp = "^(?!0{3})[0-9]{5}(?:-[0-9]{4})?$") String zipCode) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.street = street;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setName(String firstName) {
    this.firstName = firstName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  @JsonIgnore
  public boolean isEmpty() {
    return Objects.isNull(id) &&
        Objects.isNull(firstName) &&
        Objects.isNull(lastName) &&
        Objects.isNull(email) &&
        Objects.isNull(phoneNumber) &&
        Objects.isNull(street) &&
        Objects.isNull(city) &&
        Objects.isNull(state) &&
        Objects.isNull(zipCode);
  }

  @Override
  public String toString() {
    return "Customer{" +
        "id=" + id +
        ", name='" + firstName + '\'' +
        ", email='" + email + '\'' +
        '}';
  }
}


