package com.gcu.repository;

import com.gcu.model.ProductModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final List<ProductModel> products = new ArrayList<>();
    private long nextId = 1;

    public List<ProductModel> findAll() {
        return products;
    }

    public Optional<ProductModel> findById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    public void save(ProductModel product) {
        if (product.getId() == null) {
            product.setId(nextId++);
            products.add(product);
        } else {
            int index = getProductIndexById(product.getId());
            if (index != -1) {
                products.set(index, product);
            }
        }
    }

    public void deleteById(Long id) {
        int index = getProductIndexById(id);
        if (index != -1) {
            products.remove(index);
        }
    }

    private int getProductIndexById(Long id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}
