package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcu.model.ProductModel;
import com.gcu.model.UserModel;
import com.gcu.service.ProductService;
import com.gcu.service.UserService;

import java.util.List;

@Controller
public class ApplicationController {
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public ApplicationController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username,
    						@RequestParam("password") String password,
    						RedirectAttributes redirectAttributes,
    						Model model) {
    try {
    UserModel user = userService.getUserByUsername(username);
    if (user != null && passwordEncoder.matches(password, user.getPassword())) {
    	// Authentication successful
    	// You can set the user authentication status or perform additional actions here
    	return "redirect:/products";
    } else {
    	// Authentication failed, show error message
    	model.addAttribute("errorMessage", "Invalid username or password");
    	return "login";
    }
    } catch (IllegalArgumentException e) {
    	model.addAttribute("errorMessage", "Invalid username or password");
    	return "login";
    	}
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(UserModel user, RedirectAttributes redirectAttributes) {
        userService.registerUser(user);
        redirectAttributes.addFlashAttribute("successMessage", "User registered successfully");
        return "redirect:/login";
    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        List<ProductModel> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products/{id}")
    public String getProductById(@PathVariable("id") Long id, Model model) {
    	ProductModel product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-details";
    }

    @GetMapping("/products/new")
    public String showProductForm(Model model) {
        model.addAttribute("product", new ProductModel());
        return "product-form";
    }

    @PostMapping("/products/new")
    public String saveProduct(ProductModel product, RedirectAttributes redirectAttributes) {
        productService.saveProduct(product);
        redirectAttributes.addFlashAttribute("successMessage", "Product saved successfully");
        return "redirect:/products";
    }

    @GetMapping("/products/{id}/edit")
    public String showEditProductForm(@PathVariable("id") Long id, Model model) {
    	ProductModel product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-form";
    }

    @PostMapping("/products/{id}/edit")
    public String updateProduct(@PathVariable("id") Long id, ProductModel product, RedirectAttributes redirectAttributes) {
        product.setId(id);
        productService.saveProduct(product);
        redirectAttributes.addFlashAttribute("successMessage", "Product updated successfully");
        return "redirect:/products";
    }

    @PostMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        productService.deleteProduct(id);
        redirectAttributes.addFlashAttribute("successMessage", "Product deleted successfully");
        return "redirect:/products";
    }
}
