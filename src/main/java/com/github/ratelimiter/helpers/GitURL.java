package com.github.ratelimiter.dto;

import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;

public class GitURL {
    static String  accessToken = System.getenv("GIT_ACCESS_TOKEN");

    public static String getUserUrl(String name, String lastName, String location) throws UnsupportedEncodingException {

        String q = name+" "+lastName+ " in:fullname"+"+type:user"+ "+location:" + location + "&access_token="+accessToken;
        String url = "https://api.github.com/search/users?q=" + UriUtils.encodePath(q, "UTF-8");
        return url;
    }

    public static String getRepoUrl(String url) {
        return url + "?access_token="+accessToken;
    }

    public static String getCommitUrl(String url) {
        return url + "?access_token="+accessToken;
    }
}
