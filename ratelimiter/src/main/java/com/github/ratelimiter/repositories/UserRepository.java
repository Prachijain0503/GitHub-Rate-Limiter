package com.github.ratelimiter.repositories;

import com.github.ratelimiter.HTTPRequest;
import com.github.ratelimiter.dto.Commits;
import com.github.ratelimiter.dto.GitUser;
import com.github.ratelimiter.dto.Repository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class UserRepository {

    HTTPRequest httpRequest;

    public UserRepository() {
        httpRequest = new HTTPRequest();
    }

    GitUser searchGitHubUser(String name, String lastName, String location) throws Exception {
        String url = "https://api.github.com/search/users?q=" + name + "+location:" + location;
        String response = httpRequest.GET(url);
        ObjectMapper mapper = new ObjectMapper();

        CollectionType typeReference =
                TypeFactory.defaultInstance().constructCollectionType(List.class, GitUser.class);
        List<GitUser> gitUsers = mapper
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readerFor(typeReference)
                .readValue(mapper.readTree(response).path("items"));

        return gitUsers.get(0);
    }

    List<Repository> fetchRepos(String url) throws Exception {
        String response = httpRequest.GET(url+"?access_token=03092bb455db69ad51356b6fcb98db26b7a0094c");
        ObjectMapper mapper = new ObjectMapper();

        List<Repository> repositoryList = mapper
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(response, new TypeReference<List<Repository>>() {
                });

        return repositoryList;
    }

    List<Commits> fetchCommits(String url) throws Exception {
        String response = httpRequest.GET(url);

        ObjectMapper mapper = new ObjectMapper();
        CollectionType typeReference =
                TypeFactory.defaultInstance().constructCollectionType(List.class, Commits.class);

        List<Commits> commitList = mapper
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readerFor(typeReference)
                .readValue(response);

        return commitList;
    }


    public com.github.ratelimiter.model.GitUser getGitUser(String name, String lastName, String location) throws Exception {
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
