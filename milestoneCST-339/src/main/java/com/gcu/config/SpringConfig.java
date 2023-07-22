package com.gcu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gcu.repository.UserRepositoryInterface;
import com.gcu.repository.UserRepositoryImpl;

@Configuration
public class SpringConfig {
	
	@Bean(name = "userRepositoryImpl")
	UserRepositoryInterface getUsers() {
		return new UserRepositoryImpl();
	}
}
