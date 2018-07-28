package com.github.ratelimiter.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.github.ratelimiter.Exception.CustomException;
import com.github.ratelimiter.dto.Commits;
import com.github.ratelimiter.dto.Repository;
import com.github.ratelimiter.dto.SearchedGitUser;
import com.github.ratelimiter.helpers.GitHTTPRequest;
import com.github.ratelimiter.helpers.GitURL;
import com.github.ratelimiter.model.GitUser;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class GitUserRepository {

    private GitHTTPRequest httpRequest;

    public GitUserRepository() {
        httpRequest = new GitHTTPRequest();
    }

    SearchedGitUser searchGitHubUser(String name, String lastName, String location) throws Exception, CustomException {
        String url = GitURL.getUserUrl(name, lastName, location);
        String response = httpRequest.GET(url);
        ObjectMapper mapper = new ObjectMapper();

        CollectionType typeReference =
                TypeFactory.defaultInstance().constructCollectionType(List.class, SearchedGitUser.class);

        List<SearchedGitUser> searchedGitUsers = mapper
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readerFor(typeReference)
                .readValue(mapper.readTree(response).path("items"));

        if (searchedGitUsers.size() > 1)
            throw new CustomException(HttpStatus.NOT_FOUND, "Too many user found for this query.");

        if (searchedGitUsers.size() == 0)
            throw new CustomException(HttpStatus.NOT_FOUND, "User not found.");

        return searchedGitUsers.get(0);
    }

    List<Repository> fetchRepos(String url) throws Exception, CustomException {
        String repositoryUrl = GitURL.getRepoUrl(url);
        String response = httpRequest.GET(repositoryUrl);
        ObjectMapper mapper = new ObjectMapper();

        return mapper
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(response, new TypeReference<List<Repository>>() {
                });
    }

    List<Commits> fetchCommits(String url) throws Exception, CustomException {
        String commitUrl = GitURL.getCommitUrl(url);
        String response = httpRequest.GET(commitUrl);

        ObjectMapper mapper = new ObjectMapper();
        CollectionType typeReference =
                TypeFactory.defaultInstance().constructCollectionType(List.class, Commits.class);

        return mapper
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readerFor(typeReference)
                .readValue(response);
    }


    public GitUser getGitUser(String name, String lastName, String location) throws Exception, CustomException {
        List<String> repos = new ArrayList<>();
        int totalCommits = 0;

        SearchedGitUser user = searchGitHubUser(name, lastName, location);
        List<Repository> repositoryList = fetchRepos(user.repoURL);

        for (Repository repo : repositoryList) {
            repos.add(repo.reponame);
            if (repo.size > 0) {
                List<Commits> commits = fetchCommits(repo.commitURL);

                for (Commits commit : commits) {
                    if (commit.isUserCommit(user.id)) {
                        totalCommits = totalCommits + 1;
                    }
                }
            }
        }
        return new GitUser(user.id, name, lastName, 1, repos, totalCommits);
    }

}
