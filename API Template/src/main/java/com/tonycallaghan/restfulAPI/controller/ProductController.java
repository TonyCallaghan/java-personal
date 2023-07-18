package com.tonycallaghan.restfulAPI.controller;

import com.tonycallaghan.restfulAPI.controller.model.ProductID;
import com.tonycallaghan.restfulAPI.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class ProductController {

    private Map<String, Product> productMap = new ConcurrentHashMap<>();

    @PostMapping ("/product")
    public ResponseEntity<ProductID> createProduct(@RequestBody final Product product) {
        ProductID result = new ProductID(UUID.randomUUID().toString());
        product.setId(result.getId());
        productMap.put(result.getId(), product);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result);
    }

    @GetMapping("/product")
    public List<Product> getAllProducts() {
        if(productMap.isEmpty()) {
            populateProducts();
        }
        return new ArrayList<>(productMap.values());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable final String id) {
        Product product = productMap.get(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(product);
        }
    }

    @PatchMapping("/product/{id}")
    public ResponseEntity<Product> patchProduct(@PathVariable final String id, @RequestBody Product productUpdates) {
        Product product = productMap.get(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            product.setDescription(productUpdates.getDescription());
            product.setPrice(productUpdates.getPrice());
            return ResponseEntity.ok(product);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable final String id) {
        Product removedProduct = productMap.remove(id);
        if (removedProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        } else {
            return ResponseEntity.ok("Product deleted successfully");
        }
    }

    private void populateProducts() {
        Product product1 = new Product("1", "Product1", new BigDecimal("123.45"));
        Product product2 = new Product("2", "Product2", new BigDecimal("6.45"));
        Product product3 = new Product("3", "Product3", new BigDecimal("789.45"));
        productMap.put(product1.getId(), product1);
        productMap.put(product2.getId(), product2);
        productMap.put(product3.getId(), product3);
    }
}
