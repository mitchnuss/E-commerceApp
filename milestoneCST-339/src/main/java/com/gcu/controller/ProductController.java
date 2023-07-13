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

    @GetMapping("/productDetails/{id}")
    public String showProductDetails(@PathVariable("id") Long id, Model model) {
        ProductModel product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "productDetails";
    }

    @GetMapping("/updateProduct/{id}")
    public String showUpdateProductForm(@PathVariable("id") Long id, Model model) {
        ProductModel product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "updateProduct";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(ProductModel product, Model model) {
        productService.saveProduct(product);
        model.addAttribute("successMessage", "Product updated successfully");
        return "redirect:/products";
    }

    @GetMapping("/deleteProduct/{id}")
    public String showDeleteProductForm(@PathVariable("id") Long id, Model model) {
        ProductModel product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "deleteProduct";
    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("id") Long id, Model model) {
        productService.deleteProduct(id);
        model.addAttribute("successMessage", "Product deleted successfully");
        return "redirect:/products";
    }
}
