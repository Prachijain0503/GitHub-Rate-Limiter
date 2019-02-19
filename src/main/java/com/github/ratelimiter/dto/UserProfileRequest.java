package com.github.ratelimiter.dto;

public class UserProfileRequest {
    public String name;
    public String lastName;
    public String location;
    public long searchRequestId;

    public long getSearchRequestId() {
        return searchRequestId;
    }

    public UserProfileRequest setSearchRequestId(long searchRequestId) {
        this.searchRequestId = searchRequestId;
        return this;
    }

    public UserProfileRequest() {
    }

    @Override
    public String toString() {
        return "UserProfileRequest{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public UserProfileRequest(String name, String lastName, String location) {
        this.name = name;
        this.lastName = lastName;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
