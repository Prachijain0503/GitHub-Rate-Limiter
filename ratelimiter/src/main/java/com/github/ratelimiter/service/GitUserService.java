package com.github.ratelimiter.service;

import com.github.ratelimiter.dto.UserProfileRequest;
import com.github.ratelimiter.model.GitUser;
import com.github.ratelimiter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class GitUserService {
    private UserRepository repository;

    @Autowired
    public GitUserService(UserRepository repository) {
        this.repository = repository;
    }

    public GitUser get(String name, String lastName, String location) throws Exception {
        return repository.getGitUser(name, lastName, location);
    }

    public List<GitUser> find(List<UserProfileRequest> profiles) throws Exception {
        List<GitUser> user = new LinkedList<GitUser>();
        for (UserProfileRequest profileReq: profiles) {
            user.add(get(profileReq.name, profileReq.lastName, profileReq.location));
        }
        return user;
    }
}
