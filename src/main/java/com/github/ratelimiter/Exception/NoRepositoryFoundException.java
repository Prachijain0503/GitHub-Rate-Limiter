package com.github.ratelimiter.Exception;

import org.springframework.http.HttpStatus;

public class NoRepositoryFoundException extends Throwable {
    public HttpStatus statusCode = HttpStatus.NOT_FOUND;

    @Override
    public String toString() {
        return "No Repository is found for this user. Hence 0 Repository and 0 commits";
    }
}
