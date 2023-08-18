package com.gcu.repository;

import com.gcu.model.UserModel;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing user data.
 */
@Repository
public interface UserRepositoryInterface {

    /**
     * Retrieves all users.
     *
     * @return A list of all users.
     */
    List<UserModel> findAll();

    /**
     * Retrieves a user by their id.
     *
     * @param id The id of the user.
     * @return An {@link Optional} containing the user, or empty if not found.
     */
    Optional<UserModel> findById(Long id);

    /**
     * Saves the given user.
     *
     * @param user The user to be saved.
     */
    void save(UserModel user);

    /**
     * Deletes a user by their id.
     *
     * @param id The id of the user to delete.
     */
    void deleteById(Long id);

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user.
     * @return An {@link Optional} containing the user, or empty if not found.
     */
    Optional<UserModel> findByUsername(String username);

    /**
     * A test method for verifying functionality.
     */
    void test();
}
