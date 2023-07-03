package com.gcu.repository;

import com.gcu.model.ProductModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private List<ProductModel> products = new ArrayList<>();

    @Override
    public List<ProductModel> findAll() {
        return products;
    }

    @Override
    public Optional<ProductModel> findById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    @Override
    public void save(ProductModel product) {
        products.add(product);
    }

    @Override
    public void deleteById(Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }
}
