package com.quynv20.articlebackend.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }

    // If you want to further customize it, you can add constructors that accept different parameters
    // For example, you can add a constructor that accepts a message and a cause:

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // And if you want to provide the ability to enable suppression and writable stack trace:

    public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
