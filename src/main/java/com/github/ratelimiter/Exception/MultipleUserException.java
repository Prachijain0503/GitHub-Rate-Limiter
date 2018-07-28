package com.github.ratelimiter.Exception;

public class MultipleUserException extends Throwable {
    @Override
    public String toString() {
        return "More than one users found.";
    }
}
