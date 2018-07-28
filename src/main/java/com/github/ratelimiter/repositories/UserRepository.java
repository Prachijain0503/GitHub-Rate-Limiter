package com.github.ratelimiter.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.github.ratelimiter.Exception.*;
import com.github.ratelimiter.HTTPRequest;
import com.github.ratelimiter.dto.Commits;
import com.github.ratelimiter.dto.GitURL;
import com.github.ratelimiter.dto.GitUser;
import com.github.ratelimiter.dto.Repository;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class UserRepository {

    HTTPRequest httpRequest;

    public UserRepository() {
        httpRequest = new HTTPRequest();
    }

    GitUser searchGitHubUser(String name, String lastName, String location) throws Exception, MultipleUserException, NoUserFoundException, BadRequestException, RateLimitExceedException {
        String url = GitURL.getUserUrl(name, lastName, location);
        String response = httpRequest.GET(url);
        ObjectMapper mapper = new ObjectMapper();

        CollectionType typeReference =
                TypeFactory.defaultInstance().constructCollectionType(List.class, GitUser.class);
        List<GitUser> gitUsers = mapper
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readerFor(typeReference)
                .readValue(mapper.readTree(response).path("items"));
        if (gitUsers.size() > 1)
            throw new MultipleUserException();
        else if (gitUsers.size() == 0)
            throw new NoUserFoundException();
        //no user or multi user
        return gitUsers.get(0);
    }

    List<Repository> fetchRepos(String url) throws Exception, BadRequestException, NoRepositoryFoundException, RateLimitExceedException {
        String repositoryUrl = GitURL.getRepoUrl(url);
        String response = httpRequest.GET(repositoryUrl);
        ObjectMapper mapper = new ObjectMapper();

        List<Repository> repositoryList = mapper
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(response, new TypeReference<List<Repository>>() {
                });
        if (repositoryList.size() == 0)
            throw new NoRepositoryFoundException();
        return repositoryList;
    }

    List<Commits> fetchCommits(String url) throws Exception, BadRequestException, RateLimitExceedException {
        String commitUrl = GitURL.getCommitUrl(url);
        String response = httpRequest.GET(commitUrl);

        ObjectMapper mapper = new ObjectMapper();
        CollectionType typeReference =
                TypeFactory.defaultInstance().constructCollectionType(List.class, Commits.class);

        List<Commits> commitList = mapper
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readerFor(typeReference)
                .readValue(response);

        return commitList;
    }


    public com.github.ratelimiter.model.GitUser getGitUser(String name, String lastName, String location) throws Exception, NoUserFoundException, BadRequestException, MultipleUserException, NoRepositoryFoundException, RateLimitExceedException {
        List<String> repos = new ArrayList<String>();
        int totalCommits = 0;

        GitUser user = searchGitHubUser(name, lastName, location);
        List<Repository> repositoryList = fetchRepos(user.repoURL);

        for (Repository repo : repositoryList) {
            repos.add(repo.reponame);
            if (repo.size > 0) {
                List<Commits> commits = fetchCommits(repo.commitURL.replace("{/sha}", ""));

                for (Commits commit : commits) {
                    if (commit.isUserCommit(user.id)) {
                        totalCommits = totalCommits + 1;
                    }
                }
            }
        }
        return new com.github.ratelimiter.model.GitUser(user.id, name, lastName, 1, repos, totalCommits);
    }

}
