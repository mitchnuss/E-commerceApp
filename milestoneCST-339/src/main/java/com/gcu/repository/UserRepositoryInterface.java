package com.gcu.repository;

import com.gcu.model.UserModel;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryInterface {
    List<UserModel> findAll();

    Optional<UserModel> findById(Long id);

    void save(UserModel user);

    void deleteById(Long id);

    Optional<UserModel> findByUsername(String username);
    
    void test();
}
