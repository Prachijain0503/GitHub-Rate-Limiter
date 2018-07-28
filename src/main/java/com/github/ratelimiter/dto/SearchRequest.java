package com.github.ratelimiter.dto;

import javax.persistence.*;

@Entity
@Table(name = "search_request")
public class SearchRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "number_of_requests")
    private Long numberOfRequests;

    public SearchRequest() {
    }

    public SearchRequest(long numberOfRequests) {
        this.numberOfRequests = numberOfRequests;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumberOfRequests() {
        return numberOfRequests;
    }

    public void setNumberOfRequests(Long numberOfRequests) {
        this.numberOfRequests = numberOfRequests;
    }
}
