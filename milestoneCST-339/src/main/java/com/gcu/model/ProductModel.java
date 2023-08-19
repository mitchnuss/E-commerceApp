package com.gcu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Product Model class for storing different product details
 */
@Entity
public class ProductModel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String description;
    private double price;
    private int quantity;

    // Getters and setters
    /**
     * gets ID of the product
     * @return returns ID
     */
    public Long getId() {
        return id;
    }
    /**
     * sets ID of the product
     * @param id sets ID
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * gets name of the product
     * @return returns name
     */
    public String getName() {
        return name;
    }
    /**
     * sets name of the product
     * @param name sets name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * gets description of the product
     * @return returns description
     */
    public String getDescription() {
        return description;
    }
    /**
     * sets description of the product
     * @param description sets description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * gets price of the product
     * @return returns price
     */
    public double getPrice() {
        return price;
    }
    /**
     * sets price of the product
     * @param price sets price
     */
    public void setPrice(double price) {
        this.price = price;
    }
	/**
	 * gets quantity of the product
	 * @return returns quantity
	 */
    public int getQuantity() {
        return quantity;
    }
	/**
	 * sets quantity of the product
	 * @param quantity sets quantity
	 */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
