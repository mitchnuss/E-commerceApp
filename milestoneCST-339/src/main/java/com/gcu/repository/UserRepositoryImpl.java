package com.gcu.repository;

import com.gcu.model.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Repository
public class UserRepositoryImpl implements UserRepositoryInterface {

    private List<UserModel> users = new ArrayList<>();

    /**
     * Retrieve a list of all users in the repository.
     *
     * @return A list of UserModel objects representing all users.
     */
    @Override
    public List<UserModel> findAll() {
        return users;
    }

    /**
     * Retrieve a user by their unique identifier.
     *
     * @param id The unique identifier of the user.
     * @return An Optional containing the UserModel if found, otherwise empty.
     */
    @Override
    public Optional<UserModel> findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    /**
     * Save a user to the repository.
     *
     * @param user The UserModel to be saved.
     */
    @Override
    public void save(UserModel user) {
        users.add(user);
    }

    /**
     * Delete a user from the repository by their unique identifier.
     *
     * @param id The unique identifier of the user to be deleted.
     */
    @Override
    public void deleteById(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    /**
     * Find a user by their username.
     *
     * @param username The username of the user to be found.
     * @return An Optional containing the UserModel if found, otherwise empty.
     */
    @Override
    public Optional<UserModel> findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    //test method to make sure SpringConfig works
    @Override
    public void test() {
        System.out.println("Hello from the OrdersBusinessService");
    }
}
