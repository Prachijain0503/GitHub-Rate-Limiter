package com.github.ratelimiter.service;

import com.github.ratelimiter.Exception.*;
import com.github.ratelimiter.dto.UserProfileRequest;
import com.github.ratelimiter.dto.UserProfileResponse;
import com.github.ratelimiter.model.GitUser;
import com.github.ratelimiter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GitUserService {
    private UserRepository repository;

    @Autowired
    public GitUserService(UserRepository repository) {
        this.repository = repository;
    }

    public GitUser get(String name, String lastName, String location) throws Exception, MultipleUserException, NoRepositoryFoundException, BadRequestException, NoUserFoundException, RateLimitExceedException {
        return repository.getGitUser(name, lastName, location);
    }

    public List<UserProfileResponse> find(List<UserProfileRequest> profiles) throws Exception {
        List<UserProfileResponse> responses = new ArrayList<>();

        for (UserProfileRequest profileReq : profiles) {
            try {
                GitUser user = get(profileReq.name, profileReq.lastName, profileReq.location);
                responses.add(new UserProfileResponse(HttpStatus.OK, "success", user));
            } catch (BadRequestException e) {
                responses.add(new UserProfileResponse(e.statusCode, e.toString(), null));
            } catch (MultipleUserException e) {
                responses.add(new UserProfileResponse(HttpStatus.TOO_MANY_REQUESTS, e.toString(), null));
            } catch (NoUserFoundException e) {
                responses.add(new UserProfileResponse(e.statusCode, e.toString(), null));
            } catch (NoRepositoryFoundException e) {
                responses.add(new UserProfileResponse(e.statusCode, e.toString(), null));
            } catch (RateLimitExceedException e) {
                responses.add(new UserProfileResponse(e.statusCode, e.toString(), null));
            } catch (Exception e) {
                System.out.println(e);
                responses.add(new UserProfileResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null));
            }
        }
        return responses;
    }
}
