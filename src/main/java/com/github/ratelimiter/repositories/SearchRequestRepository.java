package com.github.ratelimiter.repositories;

import com.github.ratelimiter.dto.SearchRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class SearchRequestRepository {


    @PersistenceContext
    private EntityManager entityManager;

    /**
     *
     * @param searchRequest request ID and number of request
     */
    public void create(SearchRequest searchRequest) {
        entityManager.persist(searchRequest);
    }

    /**
     *
     * @param id search request id
     * @return Search request
     */
    public SearchRequest getSearchRequestById(long id) {
        return entityManager.find(SearchRequest.class, id);
    }


}
