package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.core.Authentication;
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
                            Model model, HttpSession session) {
        try {
            UserModel user = userService.getUserByUsername(username);
            if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            	//sets name of logged in user
            	session.setAttribute("loggedInUser", user);
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
    public String getProducts(Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }
        
        UserModel user = (UserModel) session.getAttribute("loggedInUser");
        List<ProductModel> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("person", user.getFirstName());
        return "products";
    }

    @GetMapping("/products/new")
    public String showCreateProductForm(Model model) {
        model.addAttribute("product", new ProductModel());
        return "createProduct";
    }

    @PostMapping("/products/new")
    public String saveProduct(ProductModel product, RedirectAttributes redirectAttributes) {
        productService.saveProduct(product);
        redirectAttributes.addFlashAttribute("successMessage", "Product saved successfully");
        return "redirect:/products";
    }
}
