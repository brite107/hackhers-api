package io.catalyte.training.teamname.exceptions;

/**
 * Exception for Bad Data Response error code 400
 */
public class BadDataResponse extends RuntimeException {
    public BadDataResponse() {}

    public BadDataResponse(String message) {
        super(message);
    }
}
