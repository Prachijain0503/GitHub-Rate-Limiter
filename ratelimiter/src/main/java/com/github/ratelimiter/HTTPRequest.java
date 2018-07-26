package com.github.ratelimiter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class HTTPRequest {

    public String GET(String url) throws Exception {


        URL obj = new URL(url);
        java.net.HttpURLConnection con = (java.net.HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");


        int responseCode = con.getResponseCode();
        if (responseCode == 200) {


            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            return response.toString();
        }

        return ("data not found");

    }
}
