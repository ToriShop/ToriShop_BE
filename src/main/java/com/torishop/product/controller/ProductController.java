package com.torishop.product.controller;

import com.torishop.product.dto.CreateProductRequest;
import com.torishop.product.dto.Product;
import com.torishop.product.dto.UpdateProductRequest;
import com.torishop.product.enums.Category;
import com.torishop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.List;

@RequestMapping("/product")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getOne(@PathVariable Integer id) {
        try {
            Product product = productService.find(id);

            return ResponseEntity.ok(product);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAll() {
        List<Product> productList = productService.findAll();

        return ResponseEntity.ok(productList);
    }

    @PostMapping()
    public ResponseEntity<?> createOne( @RequestParam("name") String name,
                                        @RequestParam("price") Integer price,
                                        @RequestParam("stock") Integer stock,
                                        @RequestParam("category") String category,
                                        @RequestParam("description") String description,
                                        @RequestParam("image") MultipartFile image) throws IOException {
        CreateProductRequest request = CreateProductRequest.builder()
                .name(name)
                .price(price)
                .stock(stock)
                .category(Category.valueOf(category))
                .description(description)
                .image(image)
                .build();
        productService.save(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> updateOne(
            @RequestParam("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("price") Integer price,
            @RequestParam("stock") Integer stock,
            @RequestParam("category") String category,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile image) {
        try {
            UpdateProductRequest request = UpdateProductRequest.builder()
                    .id(id)
                    .name(name)
                    .price(price)
                    .stock(stock)
                    .category(Category.valueOf(category))
                    .description(description)
                    .image(image)
                    .build();
            productService.modify(request);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException | IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Integer id) {
        try {
            productService.remove(id);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
