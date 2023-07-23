package com.gcu.repository;

import com.gcu.model.ProductModel;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepositoryInterface extends CrudRepository<ProductModel, Long> {
}
