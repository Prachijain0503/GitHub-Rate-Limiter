package com.github.ratelimiter.Exception;

import org.springframework.http.HttpStatus;

public class NoUserFoundException extends Throwable {
    public HttpStatus statusCode = HttpStatus.NOT_FOUND;
    @Override
    public String toString() {
        return "No user found with the given input";
    }
}
