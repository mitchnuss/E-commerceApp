package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcu.model.ProductModel;
import com.gcu.model.UserModel;
import com.gcu.service.ProductService;
import com.gcu.service.UserService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@SpringBootApplication
@Controller
public class ApplicationController {

    // Inject ProductService and UserService for database interaction
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public ApplicationController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }
    
    // Inject the BCryptPasswordEncoder for password encoding
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Home page mapping
    @GetMapping("/")
    public String home() {
        return "home"; // Return the home page template
    }

    // Login page mapping
    @GetMapping("/login")
    public String login() {
        return "login"; // Return the login page template
    }
    
    // Process login form submission
    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            RedirectAttributes redirectAttributes,
                            Model model, HttpSession session) {
        try {
            UserModel user = userService.getUserByUsername(username);
            if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            	// Set name of logged in user in session
            	session.setAttribute("loggedInUser", user);
                // Redirect to products page after successful login
                return "redirect:/products";
            } else {
                // Authentication failed, show error message
                model.addAttribute("errorMessage", "Invalid username or password");
                return "login"; // Return the login page template with error message
            }
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", "Invalid username or password");
            return "login"; // Return the login page template with error message
        }
    }

    // Registration page mapping
    @GetMapping("/register")
    public String register() {
        return "register"; // Return the registration page template
    }

    // Process user registration
    @PostMapping("/register")
    public String registerUser(UserModel user, RedirectAttributes redirectAttributes) {
        userService.registerUser(user); // Register the user
        redirectAttributes.addFlashAttribute("successMessage", "User registered successfully");
        return "redirect:/login"; // Redirect to login page after successful registration
    }

    // Products page mapping
    @GetMapping("/products")
    public String getProducts(Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login"; // Redirect to login page if user is not logged in
        }
        
        UserModel user = (UserModel) session.getAttribute("loggedInUser");
        List<ProductModel> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("person", user.getFirstName());
        return "products"; // Return the products page template
    }

    // Create new product form mapping
    @GetMapping("/products/new")
    public String showCreateProductForm(Model model) {
        model.addAttribute("product", new ProductModel());
        return "createProduct"; // Return the create product form template
    }

    // Process new product creation
    @PostMapping("/products/new")
    public String saveProduct(ProductModel product, RedirectAttributes redirectAttributes) {
        productService.saveProduct(product); // Save the new product
        redirectAttributes.addFlashAttribute("successMessage", "Product saved successfully");
        return "redirect:/products"; // Redirect to products page after successful product creation
    }
}
