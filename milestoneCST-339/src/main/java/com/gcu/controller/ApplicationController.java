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

/**
 * Controller class responsible for handling application routes and user interactions.
 */
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

    /**
     * Handle the home page route.
     * @return The home page template.
     */
    @GetMapping("/")
    public String home() {
        return "home"; // Return the home page template
    }

    /**
     * Handle the login page route.
     * @return The login page template.
     */
    @GetMapping("/login")
    public String login() {
        return "login"; // Return the login page template
    }
    
    /**
     * Handle the login form submission.
     * @param username User's username.
     * @param password User's password.
     * @param redirectAttributes Redirect attributes for flash messages.
     * @param model Model for data binding.
     * @param session HttpSession for session management.
     * @return Redirect to products page if login successful, otherwise return login page with error message.
     */
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

    /**
     * Handle the registration page route.
     * @return The registration page template.
     */
    @GetMapping("/register")
    public String register() {
        return "register"; // Return the registration page template
    }

    /**
     * Handle user registration form submission.
     * @param user UserModel containing user information.
     * @param redirectAttributes Redirect attributes for flash messages.
     * @return Redirect to login page after successful registration.
     */
    @PostMapping("/register")
    public String registerUser(UserModel user, RedirectAttributes redirectAttributes) {
        userService.registerUser(user); // Register the user
        redirectAttributes.addFlashAttribute("successMessage", "User registered successfully");
        return "redirect:/login"; // Redirect to login page after successful registration
    }

    /**
     * Handle the products page route.
     * @param model Model for data binding.
     * @param session HttpSession for session management.
     * @return Redirect to login page if user is not logged in, otherwise return products page template.
     */
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

    /**
     * Handle the create new product form route.
     * @param model Model for data binding.
     * @return The create product form template.
     */
    @GetMapping("/products/new")
    public String showCreateProductForm(Model model) {
        model.addAttribute("product", new ProductModel());
        return "createProduct"; // Return the create product form template
    }

    /**
     * Handle new product creation form submission.
     * @param product ProductModel containing product information.
     * @param redirectAttributes Redirect attributes for flash messages.
     * @return Redirect to products page after successful product creation.
     */
    @PostMapping("/products/new")
    public String saveProduct(ProductModel product, RedirectAttributes redirectAttributes) {
        productService.saveProduct(product); // Save the new product
        redirectAttributes.addFlashAttribute("successMessage", "Product saved successfully");
        return "redirect:/products"; // Redirect to products page after successful product creation
    }
}
