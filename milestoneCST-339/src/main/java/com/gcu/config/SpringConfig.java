package com.gcu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gcu.repository.UserRepositoryInterface;
import com.gcu.repository.UserRepositoryImpl;
import com.gcu.repository.ProductRepositoryInterface;
import com.gcu.repository.ProductRepositoryImpl; // Import the correct class

/**
 * Configuration class for defining Spring beans.
 */
@Configuration
public class SpringConfig {
	
    /**
     * Creates a bean for the UserRepositoryInterface using the UserRepositoryImpl implementation.
     *
     * @return The UserRepositoryInterface bean instance.
     */
	@Bean(name = "userRepositoryImpl")
	UserRepositoryInterface getUsers() {
		return new UserRepositoryImpl();
	}
	
    /**
     * Creates a bean for the ProductRepositoryInterface using the ProductRepositoryImpl implementation.
     *
     * @return The ProductRepositoryInterface bean instance.
     */
	@Bean
    public ProductRepositoryInterface productRepository() {
        return new ProductRepositoryImpl();
    }
}
