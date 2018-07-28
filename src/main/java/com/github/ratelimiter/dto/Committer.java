package com.github.ratelimiter.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

class Committer {

    String id;

    @JsonCreator
    public Committer(@JsonProperty("id") String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Committer{" +
                "id='" + id + '\'' +
                '}';
    }
}
