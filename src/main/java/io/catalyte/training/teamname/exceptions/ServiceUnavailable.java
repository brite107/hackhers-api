package io.catalyte.training.teamname.exceptions;

public class ServiceUnavailable extends RuntimeException {

  public ServiceUnavailable() {
  }

  public ServiceUnavailable(String message) {
    super(message);
  }

  public ServiceUnavailable(Exception e){
    super(e.getCause());
  }
}
