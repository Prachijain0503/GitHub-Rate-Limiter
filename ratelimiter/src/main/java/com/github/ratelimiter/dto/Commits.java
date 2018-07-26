package com.github.ratelimiter.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Commits {
    Committer committer;

    @JsonCreator
    public Commits(@JsonProperty("committer") Committer committer) {

        this.committer = committer;
    }

    public boolean isUserCommit(String id) {
        if(committer == null)
            return false;
        else
            return committer.id.equals(id);
    }

    @Override
    public String toString() {
        return "Commits{" +
                "committer=" + committer +
                '}';
    }
}


