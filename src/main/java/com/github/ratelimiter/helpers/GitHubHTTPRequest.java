package com.github.ratelimiter;

import com.github.ratelimiter.Exception.CustomException;
import org.springframework.http.HttpStatus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class GitHubHTTPRequest {

    public String GET(String url) throws CustomException, Exception {

        URL obj = new URL(url);
        java.net.HttpURLConnection con = (java.net.HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        String rateLimit = con.getHeaderField("X-RateLimit-Remaining");

        if (rateLimit.equals("0"))
            throw new CustomException(HttpStatus.FORBIDDEN, "Git rate limit exceeds");

        int responseCode = con.getResponseCode();

        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        }
        throw new CustomException(HttpStatus.valueOf(con.getResponseCode()), "Exception in Github API call");
    }
}
