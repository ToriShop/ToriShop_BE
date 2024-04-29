package com.torishop.product.controller;

import com.torishop.product.domain.ProductEntity;
import com.torishop.product.dto.Product;
import com.torishop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getOne(@PathVariable Integer id) {
        try {
            Product product = productService.findProductById(id);

            return ResponseEntity.ok(product);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
