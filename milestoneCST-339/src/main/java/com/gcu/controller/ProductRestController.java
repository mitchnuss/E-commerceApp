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

/**
 * Controller class that handles API requests related to products.
 */
@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Retrieves a list of all products (authenticated).
     *
     * @param request The HTTP request object.
     * @return A ResponseEntity containing a list of products or an unauthorized response.
     */
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

    /**
     * Retrieves a product by its ID (authenticated).
     *
     * @param id      The ID of the product to retrieve.
     * @param request The HTTP request object.
     * @return A ResponseEntity containing the requested product or an unauthorized response.
     */
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

    /**
     * Checks if a user is logged in.
     *
     * @param request The HTTP request object.
     * @return True if the user is logged in, false otherwise.
     */
    private boolean isUserLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute("loggedInUser") != null;
    }
}
