package com.gcu.controller;

import com.gcu.model.ProductModel;
import com.gcu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Show details of a specific product
    @GetMapping("/productDetails/{id}")
    public String showProductDetails(@PathVariable("id") Long id, Model model) {
        ProductModel product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "productDetails"; // Return the product details template
    }

    // Show form to update a product
    @GetMapping("/updateProduct/{id}")
    public String showUpdateProductForm(@PathVariable("id") Long id, Model model) {
        ProductModel product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "updateProduct"; // Return the update product form template
    }

    // Process product update
    @PostMapping("/updateProduct")
    public String updateProduct(ProductModel product, Model model) {
        productService.saveProduct(product); // Save the updated product
        model.addAttribute("successMessage", "Product updated successfully");
        return "redirect:/products"; // Redirect to products page after successful update
    }

    // Show form to delete a product
    @GetMapping("/deleteProduct/{id}")
    public String showDeleteProductForm(@PathVariable("id") Long id, Model model) {
        ProductModel product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "deleteProduct"; // Return the delete product form template
    }

    // Process product deletion
    @PostMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("id") Long id, Model model) {
        productService.deleteProduct(id); // Delete the product
        model.addAttribute("successMessage", "Product deleted successfully");
        return "redirect:/products"; // Redirect to products page after successful deletion
    }
}
