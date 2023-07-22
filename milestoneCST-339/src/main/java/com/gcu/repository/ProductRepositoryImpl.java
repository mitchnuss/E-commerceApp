package com.gcu.repository;

import com.gcu.model.ProductModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryInterface {
    private final List<ProductModel> products = new ArrayList<>();
    private long nextId = 1;

    @Override
    public <S extends ProductModel> S save(S entity) {
        if (entity.getId() == null) {
            entity.setId(nextId++);
            products.add(entity);
        } else {
            int index = getProductIndexById(entity.getId());
            if (index != -1) {
                products.set(index, entity);
            }
        }
        return entity;
    }

    @Override
    public <S extends ProductModel> Iterable<S> saveAll(Iterable<S> entities) {
        List<S> savedEntities = new ArrayList<>();
        entities.forEach(entity -> savedEntities.add(save(entity)));
        return savedEntities;
    }

    @Override
    public Optional<ProductModel> findById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    @Override
    public Iterable<ProductModel> findAll() {
        return products;
    }

    @Override
    public Iterable<ProductModel> findAllById(Iterable<Long> ids) {
        List<ProductModel> matchingProducts = new ArrayList<>();
        for (Long id : ids) {
            findById(id).ifPresent(matchingProducts::add);
        }
        return matchingProducts;
    }

    @Override
    public boolean existsById(Long id) {
        return findById(id).isPresent();
    }

    @Override
    public long count() {
        return products.size();
    }

    @Override
    public void deleteById(Long id) {
        int index = getProductIndexById(id);
        if (index != -1) {
            products.remove(index);
        }
    }

    @Override
    public void delete(ProductModel entity) {
        deleteById(entity.getId());
    }

    @Override
    public void deleteAll(Iterable<? extends ProductModel> entities) {
        entities.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        products.clear();
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        ids.forEach(this::deleteById);
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
