package com.github.ratelimiter.service;

import com.github.ratelimiter.dto.SearchRequest;
import com.github.ratelimiter.dto.SearchResponse;
import com.github.ratelimiter.dto.UserProfileRequest;
import com.github.ratelimiter.repositories.SearchRequestRepository;
import com.github.ratelimiter.repositories.UserProfileResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class SearchService {

    private JmsTemplate taskQueue;
    private SearchRequestRepository searchRequestRepository;
    private UserProfileResponseRepository userProfileResponseRepository;

    @Autowired
    public SearchService(JmsTemplate taskQueue,
                         SearchRequestRepository searchRequestRepository,
                         UserProfileResponseRepository UserProfileResponseRepository) {

        this.taskQueue = taskQueue;
        this.searchRequestRepository = searchRequestRepository;
        this.userProfileResponseRepository = UserProfileResponseRepository;
    }

    /**
     *
     * @param profiles List of requested user profile
     * @return Search request
     */
    @Transactional
    public SearchRequest search(List<UserProfileRequest> profiles) {
        SearchRequest request = new SearchRequest(profiles.size());
        searchRequestRepository.create(request);
        for (UserProfileRequest profileReq : profiles) {
            taskQueue.convertAndSend("GitUserTransactionQueue", profileReq.setSearchRequestId(request.getId()));
        }
        return request;
    }

    /**
     *
     * @param searchRequestId search request id
     * @return searched result
     */
    @Transactional
    public SearchResponse searchResponse(Long searchRequestId) {
        int gitProfileCount = userProfileResponseRepository.getGitProfileCount(searchRequestId);
        SearchRequest searchRequest = searchRequestRepository.getSearchRequestById(searchRequestId);
        if (searchRequest.getNumberOfRequests() == gitProfileCount) {
            return new SearchResponse(searchRequestId, HttpStatus.OK, userProfileResponseRepository.getAllGitProfiles(searchRequestId));
        }

        return new SearchResponse(searchRequestId, HttpStatus.PROCESSING, Collections.emptyList());
    }
}
