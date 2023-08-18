package com.gcu.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The main class for launching the application.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.gcu")
public class MilestoneCst339Application {

    /**
     * The main method to start the application.
     *
     * @param args Command-line arguments (not used in this case).
     */
    public static void main(String[] args) {
        SpringApplication.run(MilestoneCst339Application.class, args);
    }

}
