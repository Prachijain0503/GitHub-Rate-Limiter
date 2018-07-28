package com.github.ratelimiter.controller;

import com.github.ratelimiter.dto.SearchRequest;
import com.github.ratelimiter.dto.SearchResponse;
import com.github.ratelimiter.dto.UserProfileRequest;
import com.github.ratelimiter.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchController {
    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public SearchRequest findUser(@RequestBody List<UserProfileRequest> profiles) throws Exception {
        return searchService.search(profiles);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public SearchResponse findUser(@RequestParam Long searchRequestId) throws Exception {
        return searchService.searchResponse(searchRequestId);
    }
}
