package com.gcu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * User Model class for storing different user details
 */
@Entity
public class UserModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String username;
    private String password;
    

    /**
     * gets ID of the user
     * @return returns ID
     */
    public Long getId() {
        return id;
    }
    /**
     * sets ID of the user
     * @param id sets ID
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * gets first name of the user
     * @return returns first name
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * sets first name of the user
     * @param firstName sets first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
	/**
	 * gets last name of the user
	 * @return returns last name
	 */
    public String getLastName() {
        return lastName;
    }
    /**
     * sets last name of the user
     * @param lastName sets last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * gets email of the user
     * @return returns email
     */
    public String getEmail() {
        return email;
    }
	/**
	 * sets email of the user
	 * @param email sets email
	 */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * gets phone of the user
     * @return returns phone
     */
    public String getPhone() {
        return phone;
    }
    /**
     * sets phone of the user
     * @param phone sets phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**
     * gets username of the user
     * @return returns username
     */
    public String getUsername() {
        return username;
    }
    /**
     * sets username of the user
     * @param username sets username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * gets password of the user
     * @return returns password
     */
    public String getPassword() {
        return password;
    }
    /**
     * sets password of the user
     * @param password sets password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}