package com.github.ratelimiter.dto;

import com.github.ratelimiter.model.GitUser;
import org.springframework.http.HttpStatus;

import javax.persistence.*;

@Entity
@Table(name = "git_profile")
public class UserProfileResponse {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name = "status_code")
    public HttpStatus statusCode;

    @Column(name = "search_request_id")
    public long searchRequestId;

    @OneToOne(cascade = {CascadeType.ALL})
    private GitUser user;

    private String msg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public long getSearchRequestId() {
        return searchRequestId;
    }

    public void setSearchRequestId(long searchRequestId) {
        this.searchRequestId = searchRequestId;
    }

    public GitUser getUser() {
        return user;
    }

    public void setUser(GitUser user) {
        this.user = user;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UserProfileResponse() {
    }

    public UserProfileResponse(HttpStatus statusCode, String msg, GitUser user, long searchRequestId) {
        this.statusCode = statusCode;
        this.msg = msg;
        this.user = user;
        this.searchRequestId = searchRequestId;
    }


}
