package com.example.opentrivia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class OpenTriviaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenTriviaApplication.class, args);
    }

}
