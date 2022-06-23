package com.example.logisticapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class LogisticApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogisticApiApplication.class, args);
    }

}
