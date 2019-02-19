package com.github.ratelimiter.helpers;

import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;

public class GitURL {
    static String accessToken = System.getenv("GIT_ACCESS_TOKEN");

    /**
     *
     * @param name name of the user
     * @param lastName last name of the user
     * @param location location of the user
     * @return formatted user URL
     * @throws UnsupportedEncodingException
     */
    public static String getUserUrl(String name, String lastName, String location) throws UnsupportedEncodingException {

        String q = name + " " + lastName + " in:fullname" + "+type:user" + "+location:" + location + "&access_token=" + accessToken;
        String url = "https://api.github.com/search/users?q=" + UriUtils.encodePath(q, "UTF-8");
        return url;
    }

    /**
     *
     * @param url Repository URL
     * @return formatted repository URL
     */
    public static String getRepoUrl(String url) {
        return url + "?access_token=" + accessToken;
    }

    /**
     *
     * @param url Commit URL
     * @return formatted commit URL
     */
    public static String getCommitUrl(String url) {
        url = url.replace("{/sha}", "");
        return url + "?access_token=" + accessToken;
    }
}
