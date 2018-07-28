package com.github.ratelimiter.Exception;

import org.springframework.http.HttpStatus;

public class RateLimitExceedException extends Throwable {

    public HttpStatus statusCode = HttpStatus.FORBIDDEN;
    @Override
    public String toString() {
        return "Rate Limit exceeded";
    }
}
