package com.github.ratelimiter.dto;

public class UserProfileRequest {
    public String name;
    public String lastName;
    public String  location;

    public UserProfileRequest() {
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
