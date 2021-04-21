package io.catalyte.training.teamname.exceptions;

/**
 * Unique Field Violation is thrown when a 409 occurs
 */
public class UniqueFieldViolation extends RuntimeException{
  public UniqueFieldViolation() {}

  public UniqueFieldViolation(String message) {
    super(message);
  }
}
