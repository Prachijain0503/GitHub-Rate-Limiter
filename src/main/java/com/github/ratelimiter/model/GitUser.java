package com.github.ratelimiter.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class GitUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dbId;

    private String id;
    private String name;
    private String lastName;
    private double score;
    @ElementCollection
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

    public GitUser() {
    }

    public Long getDbId() {

        return dbId;
    }

    public void setDbId(Long dbId) {
        this.dbId = dbId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<String> getRepoNames() {
        return repoNames;
    }

    public void setRepoNames(List<String> repoNames) {
        this.repoNames = repoNames;
    }

    public int getTotalCommits() {
        return totalCommits;
    }

    public void setTotalCommits(int totalCommits) {
        this.totalCommits = totalCommits;
    }
}



