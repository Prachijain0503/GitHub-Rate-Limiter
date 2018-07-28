package com.github.ratelimiter.repositories;

import com.github.ratelimiter.dto.SearchRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class SearchRequestRepository {


    @PersistenceContext
    private EntityManager entityManager;

    public void create(SearchRequest searchRequest) {
        entityManager.persist(searchRequest);
    }

    public SearchRequest getSearchRequestById(long id) {
        return entityManager.find(SearchRequest.class, id);
    }


}
