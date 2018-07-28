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

    /**
     *
     * @param searchRequestId search request id
     * @return List of searched git profile
     */
    public List<UserProfileResponse> getAllGitProfiles(long searchRequestId) {

        return entityManager.createQuery(
                "FROM UserProfileResponse where searchRequestId =:searchRequestId")
                .setParameter("searchRequestId", searchRequestId)
                .getResultList();
    }

    /**
     *
     * @param searchRequestId search request id
     * @return count of searched git profile
     */
    public int getGitProfileCount(long searchRequestId) {
        return getAllGitProfiles(searchRequestId).size();
    }
}
