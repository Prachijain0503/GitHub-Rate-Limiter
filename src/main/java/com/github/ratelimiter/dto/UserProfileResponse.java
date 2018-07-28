package com.github.ratelimiter.dto;

import com.github.ratelimiter.model.GitUser;
import org.springframework.http.HttpStatus;

public class UserProfileResponse {

    int statusCode;
    String msg;
    GitUser user;

    public UserProfileResponse(HttpStatus statusCode, String msg, GitUser user) {
        this.statusCode = statusCode;
        this.msg = msg;
        this.user = user;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public GitUser getUser() {
        return user;
    }
}
