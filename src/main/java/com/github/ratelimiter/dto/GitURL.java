package com.github.ratelimiter.dto;

import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class GitURL {

    public static String getUserUrl(String name, String lastName, String location) throws UnsupportedEncodingException {
        String q = name + "+location:" + location + "&access_token=";
        String url = "https://api.github.com/search/users?q=" + UriUtils.encodePath(q, "UTF-8");
        return url;
    }

    public static String getRepoUrl(String url) {
       return url+"?access_token=";
    }

    public static String getCommitUrl(String url) {
        return url+"?access_token=";
    }
}
