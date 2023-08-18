package com.gcu.repository;

import com.gcu.model.ProductModel;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface for accessing and managing ProductModel entities in the database.
 */
public interface ProductRepositoryInterface extends CrudRepository<ProductModel, Long> {
}
