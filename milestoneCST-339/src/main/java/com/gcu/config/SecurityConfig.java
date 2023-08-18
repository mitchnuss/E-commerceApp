package com.gcu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuration class for security-related beans.
 */
@Configuration
public class SecurityConfig {

    /**
     * Creates a BCryptPasswordEncoder bean used for password hashing.
     *
     * @return The BCryptPasswordEncoder bean instance.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
