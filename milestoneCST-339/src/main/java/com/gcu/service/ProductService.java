package com.gcu.service;

import com.gcu.model.ProductModel;
import com.gcu.repository.ProductRepositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
	private final ProductRepositoryImpl productRepository;

    public ProductService(ProductRepositoryImpl productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductModel> getAllProducts() {
        Iterable<ProductModel> iterable = productRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    public ProductModel getProductById(Long id) {
        Optional<ProductModel> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new IllegalArgumentException("Invalid product ID: " + id);
        }
    }

    public void saveProduct(ProductModel product) {
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
