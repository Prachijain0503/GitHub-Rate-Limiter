package com.github.ratelimiter.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GitUser {

    public String id;
    public String repoURL;

    @JsonCreator
    public GitUser(@JsonProperty("id") String id, @JsonProperty("repos_url") String repoURL) {
        this.id = id;
        this.repoURL = repoURL;
    }


    @Override
    public String toString() {
        return "GitUser{" +
                "id=" + id +
                ", repoURL='" + repoURL + '\'' +
                '}';
    }
}
