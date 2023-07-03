package com.gcu.repository;

import java.util.List;
import java.util.Optional;

import com.gcu.model.ProductModel;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository {
    List<ProductModel> findAll();

    Optional<ProductModel> findById(Long id);

    void save(ProductModel product);

    void deleteById(Long id);
}
