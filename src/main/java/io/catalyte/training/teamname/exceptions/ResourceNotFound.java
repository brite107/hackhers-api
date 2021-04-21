package io.catalyte.training.teamname.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Resource Not Found is thrown when a 404 error occurs
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {

  public ResourceNotFound() {
  }

  public ResourceNotFound(String message) {
    super(message);
  }
}
