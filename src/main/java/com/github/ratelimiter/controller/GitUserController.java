package com.github.ratelimiter.controller;

import com.github.ratelimiter.Exception.*;
import com.github.ratelimiter.dto.UserProfileRequest;
import com.github.ratelimiter.dto.UserProfileResponse;
import com.github.ratelimiter.service.GitUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GitUserController {
    private GitUserService gitUserService;

    @Autowired
    public GitUserController(GitUserService gitUserService) {
        this.gitUserService = gitUserService;
    }

    @RequestMapping(value = "/user/{name}/{lastName}/{location}", method = RequestMethod.GET)
    public com.github.ratelimiter.model.GitUser user(@PathVariable String name, @PathVariable String lastName, @PathVariable String location) throws Exception, MultipleUserException, NoRepositoryFoundException, BadRequestException, NoUserFoundException, RateLimitExceedException {
        return gitUserService.get(name, lastName, location);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public List<UserProfileResponse> findUser(@RequestBody List<UserProfileRequest> profiles) throws Exception {
        return gitUserService.find(profiles);
    }
}
