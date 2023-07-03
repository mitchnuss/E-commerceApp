package com.gcu.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.gcu")
public class MilestoneCst339Application {

    public static void main(String[] args) {
        SpringApplication.run(MilestoneCst339Application.class, args);
    }

}
