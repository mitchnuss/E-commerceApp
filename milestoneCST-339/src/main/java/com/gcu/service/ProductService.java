package com.gcu.service;

import com.gcu.model.ProductModel;
import com.gcu.repository.ProductRepositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
/**
 * Product Service class to perform CRUD operations
 */
@Service
public class ProductService {
	private final ProductRepositoryImpl productRepository;
	/**
	 * constructor
	 * @param productRepository 
	 */
    public ProductService(ProductRepositoryImpl productRepository) {
        this.productRepository = productRepository;
    }
    /**
     * Gets all products
     * @return all products
     */
    public List<ProductModel> getAllProducts() {
        Iterable<ProductModel> iterable = productRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }
    /**
     * method to get a product by id
     * @param id id
     * @return product by id
     */
    public ProductModel getProductById(Long id) {
        Optional<ProductModel> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new IllegalArgumentException("Invalid product ID: " + id);
        }
    }
    /**
     * method to save a product
     * @param product product
     */
    public void saveProduct(ProductModel product) {
        productRepository.save(product);
    }
    /**
     * deletes a product
     * @param id id
     */
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
