package com.github.ratelimiter.model;

import java.util.List;

public class GitUser {

    private final String id;
    private final String name;
    private String lastName;
    private double score;
    private List<String> repoNames;
    private int totalCommits;

    public GitUser(String id, String name, String lastName, double score, List<String> repoNames, int totalCommits) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.score = score;
        this.repoNames = repoNames;
        this.totalCommits = totalCommits;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public double getScore() {
        return score;
    }

    public List<String> getRepoNames() {
        return repoNames;
    }

    public int getTotalCommits() {
        return totalCommits;
    }

    @Override
    public String toString() {
        return "SearchedGitUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", score=" + score +
                ", repoNames=" + repoNames +
                ", totalCommits=" + totalCommits +
                '}';
    }
}



