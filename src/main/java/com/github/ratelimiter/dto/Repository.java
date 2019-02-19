package com.github.ratelimiter.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Repository {

    public String reponame;
    public String commitURL;
    public double size;

    @JsonCreator
    public Repository(@JsonProperty("name") String reponame, @JsonProperty("commits_url") String commitURL, @JsonProperty("size") double size) {
        this.reponame = reponame;
        this.commitURL = commitURL;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Repository{" +
                ", reponame='" + reponame + '\'' +
                ", commitURL='" + commitURL + '\'' +
                '}';
    }
}
