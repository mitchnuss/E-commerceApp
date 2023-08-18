package com.gcu.repository;

import com.gcu.model.ProductModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the ProductRepositoryInterface that uses an in-memory list for data storage.
 */
@Repository
public class ProductRepositoryImpl implements ProductRepositoryInterface {
    private final List<ProductModel> products = new ArrayList<>();
    private long nextId = 1;

    /**
     * Saves the given entity, either by adding a new entity or updating an existing one.
     *
     * @param entity The entity to be saved.
     * @param <S>    The type of the entity.
     * @return The saved entity.
     */
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

    /**
     * Saves all given entities.
     *
     * @param entities The entities to be saved.
     * @param <S>      The type of the entities.
     * @return The saved entities.
     */
    @Override
    public <S extends ProductModel> Iterable<S> saveAll(Iterable<S> entities) {
        List<S> savedEntities = new ArrayList<>();
        entities.forEach(entity -> savedEntities.add(save(entity)));
        return savedEntities;
    }

    /**
     * Retrieves an entity by its id.
     *
     * @param id The id of the entity.
     * @return An {@link Optional} containing the entity, or empty if not found.
     */
    @Override
    public Optional<ProductModel> findById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    /**
     * Retrieves all entities.
     *
     * @return An {@link Iterable} containing all entities.
     */
    @Override
    public Iterable<ProductModel> findAll() {
        return products;
    }

    /**
     * Retrieves all entities with the given ids.
     *
     * @param ids The ids of the entities to retrieve.
     * @return An {@link Iterable} containing the matching entities.
     */
    @Override
    public Iterable<ProductModel> findAllById(Iterable<Long> ids) {
        List<ProductModel> matchingProducts = new ArrayList<>();
        for (Long id : ids) {
            findById(id).ifPresent(matchingProducts::add);
        }
        return matchingProducts;
    }

    /**
     * Checks if an entity with the given id exists.
     *
     * @param id The id of the entity to check.
     * @return {@code true} if the entity exists, {@code false} otherwise.
     */
    @Override
    public boolean existsById(Long id) {
        return findById(id).isPresent();
    }

    /**
     * Returns the count of all entities.
     *
     * @return The count of all entities.
     */
    @Override
    public long count() {
        return products.size();
    }

    /**
     * Deletes the entity with the given id.
     *
     * @param id The id of the entity to delete.
     */
    @Override
    public void deleteById(Long id) {
        int index = getProductIndexById(id);
        if (index != -1) {
            products.remove(index);
        }
    }

    /**
     * Deletes the given entity.
     *
     * @param entity The entity to delete.
     */
    @Override
    public void delete(ProductModel entity) {
        deleteById(entity.getId());
    }

    /**
     * Deletes all given entities.
     *
     * @param entities The entities to delete.
     */
    @Override
    public void deleteAll(Iterable<? extends ProductModel> entities) {
        entities.forEach(this::delete);
    }

    /**
     * Deletes all entities.
     */
    @Override
    public void deleteAll() {
        products.clear();
    }

    /**
     * Deletes all entities with the given ids.
     *
     * @param ids The ids of the entities to delete.
     */
    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        ids.forEach(this::deleteById);
    }

    /**
     * Returns the index of the product with the given id in the list.
     *
     * @param id The id of the product.
     * @return The index of the product, or -1 if not found.
     */
    private int getProductIndexById(Long id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}
