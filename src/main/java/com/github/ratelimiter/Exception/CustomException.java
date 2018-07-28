package com.github.ratelimiter.Exception;

import org.springframework.http.HttpStatus;

public class CustomException extends Throwable {
    HttpStatus statusCode;

    public CustomException( HttpStatus statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
