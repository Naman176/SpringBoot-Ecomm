package com.ecommerce.project.exceptions;

public class APIException extends RuntimeException{
    private static final long serailVersionId = 1L;

    public APIException() {
    }

    public APIException(String message) {
        super(message);
    }
}
