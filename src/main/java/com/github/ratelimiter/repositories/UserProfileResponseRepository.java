package com.github.ratelimiter.repositories;

import com.github.ratelimiter.dto.UserProfileResponse;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserProfileResponseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public UserProfileResponseRepository() {
    }


    public void create(UserProfileResponse userProfileResponse) {
        entityManager.persist(userProfileResponse);
    }

    public List<UserProfileResponse> getAllGitProfiles(long searchRequestId) {

        return entityManager.createQuery(
                "FROM UserProfileResponse where searchRequestId =:searchRequestId")
                .setParameter("searchRequestId", searchRequestId)
                .getResultList();
    }

    public int getGitProfileCount(long searchRequestId) {
        return getAllGitProfiles(searchRequestId).size();
    }
}
