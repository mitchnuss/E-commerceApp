package com.gcu.repository;

import com.gcu.model.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private List<UserModel> users = new ArrayList<>();

    @Override
    public List<UserModel> findAll() {
        return users;
    }

    @Override
    public Optional<UserModel> findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public void save(UserModel user) {
        users.add(user);
    }

    @Override
    public void deleteById(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    @Override
    public Optional<UserModel> findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }
}
