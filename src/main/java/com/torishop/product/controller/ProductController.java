package com.torishop.product.controller;

import com.torishop.product.domain.ProductEntity;
import com.torishop.product.dto.CreateProductRequest;
import com.torishop.product.dto.Product;
import com.torishop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/product")
    public ResponseEntity<?> createOne(@RequestBody CreateProductRequest request) {
        productService.save(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
