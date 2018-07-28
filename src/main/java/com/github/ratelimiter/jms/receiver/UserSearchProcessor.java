package com.github.ratelimiter.jms.receiver;

import com.github.ratelimiter.Exception.CustomException;
import com.github.ratelimiter.dto.UserProfileRequest;
import com.github.ratelimiter.dto.UserProfileResponse;
import com.github.ratelimiter.model.GitUser;
import com.github.ratelimiter.service.UserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class UserSearchProcessor {


    private UserSearchService userSearchService;

    @Autowired
    public UserSearchProcessor(UserSearchService userSearchService) {
        this.userSearchService = userSearchService;
    }

    /**
     *
     * @param transaction each task of queue
     * @throws Exception when some issue occurred during processing queued tasks
     * @throws CustomException when no data or multiple data found for user search
     */
    @Transactional
    @JmsListener(destination = "GitUserTransactionQueue")
    public void process(UserProfileRequest transaction) throws Exception, CustomException {

        UserProfileResponse response;
        try {
            GitUser user = userSearchService.searchUser(transaction.name, transaction.lastName, transaction.location);
            response = new UserProfileResponse(HttpStatus.OK, "success", user, transaction.searchRequestId);

        } catch (CustomException e) {
            if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
                throw e;
            }
            response = new UserProfileResponse(e.getStatusCode(), e.getMessage(), null, transaction.searchRequestId);
        }
        userSearchService.saveUserProfile(response);
    }

}
