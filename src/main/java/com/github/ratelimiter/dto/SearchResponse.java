package com.github.ratelimiter.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public class SearchResponse {

    private final long requestId;
    private final HttpStatus status;
    private final List<UserProfileResponse> userProfiles;

    public SearchResponse(long requestId, HttpStatus status, List<UserProfileResponse> userProfiles) {
        this.requestId = requestId;
        this.status = status;
        this.userProfiles = userProfiles;
    }

    public long getRequestId() {
        return requestId;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public List<UserProfileResponse> getUserProfiles() {
        return userProfiles;
    }
}
