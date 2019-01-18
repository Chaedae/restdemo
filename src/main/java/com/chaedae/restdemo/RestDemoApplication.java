package com.chaedae.restdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan( basePackages = "com.chaedae.restdemo.**")
public class RestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestDemoApplication.class, args);
    }

}

