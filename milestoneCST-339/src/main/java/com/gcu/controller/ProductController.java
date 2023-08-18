package com.gcu.controller;

import com.gcu.model.ProductModel;
import com.gcu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class that handles product-related web requests.
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Displays details of a specific product.
     *
     * @param id    The ID of the product to display details for.
     * @param model The model to hold data for the view.
     * @return The template name for displaying product details.
     */
    @GetMapping("/productDetails/{id}")
    public String showProductDetails(@PathVariable("id") Long id, Model model) {
        ProductModel product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "productDetails"; // Return the product details template
    }

    /**
     * Displays the form to update a product.
     *
     * @param id    The ID of the product to update.
     * @param model The model to hold data for the view.
     * @return The template name for updating a product.
     */
    @GetMapping("/updateProduct/{id}")
    public String showUpdateProductForm(@PathVariable("id") Long id, Model model) {
        ProductModel product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "updateProduct"; // Return the update product form template
    }

    /**
     * Handles the submission of an updated product.
     *
     * @param product The updated product data.
     * @param model   The model to hold data for the view.
     * @return A redirect to the products page after successful update.
     */
    @PostMapping("/updateProduct")
    public String updateProduct(ProductModel product, Model model) {
        productService.saveProduct(product); // Save the updated product
        model.addAttribute("successMessage", "Product updated successfully");
        return "redirect:/products"; // Redirect to products page after successful update
    }

    /**
     * Displays the form to delete a product.
     *
     * @param id    The ID of the product to delete.
     * @param model The model to hold data for the view.
     * @return The template name for deleting a product.
     */
    @GetMapping("/deleteProduct/{id}")
    public String showDeleteProductForm(@PathVariable("id") Long id, Model model) {
        ProductModel product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "deleteProduct"; // Return the delete product form template
    }

    /**
     * Handles the deletion of a product.
     *
     * @param id    The ID of the product to delete.
     * @param model The model to hold data for the view.
     * @return A redirect to the products page after successful deletion.
     */
    @PostMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("id") Long id, Model model) {
        productService.deleteProduct(id); // Delete the product
        model.addAttribute("successMessage", "Product deleted successfully");
        return "redirect:/products"; // Redirect to products page after successful deletion
    }
}
