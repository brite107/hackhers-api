package io.catalyte.training.teamname.constants;

/**
 * Messages stored as constants to maintain uniformity within the program
 */

public class StringConstants {

  public static final String NOT_FOUND = "Not Found";
  public static final String SERVER_ERROR = "Server Error";
  public static final String BAD_REQUEST = "Bad Request, check your input and try again";

  // Auth
  public static final String UNAUTHORIZED = "Unauthorized";
  public static final String INVALID_EMAIL_PASSWORD = "Invalid email or password.";
  public static final String ISSUER = "hackhers-api";
  public static final String SECRET_KEY = "secret";
  public static final String CLAIMS_ATTRIBUTE = "claims";
  public static final String EMAIL_ATTRIBUTE = "email";
  public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String MISSING_INVALID_ERROR = "Missing or invalid Authorization header";
  public static final String APPLICATION_JSON = "application/json";

  public static final String VALIDATION_ERROR = "Validation Error";
  public static final String REQUIRED_FIELD = " is a required field";
  public static final String GENERATED_ID = "Primary Key field automatically generated";
  public static final String CONFLICT = "Already exists in the database";
  public static final String BAD_DATA = "Bad Data";
  public static final String UNEXPECTED_ERROR = "Unexpected Server Error";
  public static final String CONTEXT_CUSTOMERS = "/customers";
}
