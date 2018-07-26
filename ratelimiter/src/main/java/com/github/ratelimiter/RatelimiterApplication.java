package com.github.ratelimiter;

import com.github.ratelimiter.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RatelimiterApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RatelimiterApplication.class, args);
    }
}
