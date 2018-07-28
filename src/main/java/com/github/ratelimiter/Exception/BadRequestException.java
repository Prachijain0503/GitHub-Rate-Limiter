package com.github.ratelimiter.Exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends Throwable {
    public HttpStatus statusCode = HttpStatus.BAD_REQUEST;


    @Override
    public String toString() {
        return "Bad Request - Invalid URL. The request could not be understood by the server due to malformed syntax.";
    }
}
