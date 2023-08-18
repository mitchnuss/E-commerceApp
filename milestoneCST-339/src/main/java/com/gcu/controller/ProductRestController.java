package com.gcu.controller;

import com.gcu.model.ProductModel;
import com.gcu.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    // Get all products (authenticated)
    @GetMapping
    public ResponseEntity<?> getAllProducts(HttpServletRequest request) {
        if (!isUserLoggedIn(request)) {
            // Unauthorized access response with links to Home and Login pages
            String errorMessage = "Unauthorized access. Please log in.";
            String homePageLink = "/";
            String loginPageLink = "/login";
            
            String htmlMessage = "<html><body>"
                    + "<h1>" + errorMessage + "</h1>"
                    + "<p><a href='" + homePageLink + "'>Go to Home</a></p>"
                    + "<p><a href='" + loginPageLink + "'>Log In</a></p>"
                    + "</body></html>";
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .contentType(MediaType.TEXT_HTML)
                    .body(htmlMessage);
        }
        
        List<ProductModel> products = productService.getAllProducts();
        return ResponseEntity.ok(products); // Return a list of products
    }

    // Get a product by ID (authenticated)
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id, HttpServletRequest request) {
        if (!isUserLoggedIn(request)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
        
        ProductModel product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product); // Return the requested product
    }

    // Check if user is logged in
    private boolean isUserLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute("loggedInUser") != null;
    }
}
