package com.github.ratelimiter.service;

import com.github.ratelimiter.Exception.CustomException;
import com.github.ratelimiter.dto.UserProfileResponse;
import com.github.ratelimiter.model.GitUser;
import com.github.ratelimiter.repositories.GitUserRepository;
import com.github.ratelimiter.repositories.UserProfileResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSearchService {

    private GitUserRepository gitUserRepository;
    private UserProfileResponseRepository userProfileResponseRepository;

    @Autowired
    public UserSearchService(GitUserRepository gitUserRepository,
                             UserProfileResponseRepository userProfileResponseRepository) {
        this.gitUserRepository = gitUserRepository;
        this.userProfileResponseRepository = userProfileResponseRepository;
    }

    /**
     *
     * @param name name of the user
     * @param lastName last name of the user
     * @param location location of the user
     * @return information of git user
     * @throws CustomException when no or more than one data found
     * @throws Exception when some issue occurred during processing queued tasks to retry it
     */
    public GitUser searchUser(String name, String lastName, String location) throws CustomException, Exception {
        return gitUserRepository.getGitUser(name, lastName, location);
    }

    public void saveUserProfile(UserProfileResponse userProfile) {
        userProfileResponseRepository.create(userProfile);
    }
}
