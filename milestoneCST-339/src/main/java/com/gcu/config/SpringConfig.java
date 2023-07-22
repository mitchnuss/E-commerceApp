package com.gcu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gcu.repository.UserRepositoryInterface;
import com.gcu.repository.UserRepositoryImpl;
import com.gcu.repository.ProductRepositoryInterface;
import com.gcu.repository.ProductRepositoryImpl; // Import the correct class

@Configuration
public class SpringConfig {
	
	@Bean(name = "userRepositoryImpl")
	UserRepositoryInterface getUsers() {
		return new UserRepositoryImpl();
	}
	
	@Bean
    public ProductRepositoryInterface productRepository() {
        return new ProductRepositoryImpl();
    }
}
