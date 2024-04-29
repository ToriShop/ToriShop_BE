package com.torishop.product.service.impl;

import com.torishop.product.domain.ProductEntity;
import com.torishop.product.domain.ProductRepository;
import com.torishop.product.dto.CreateProductRequest;
import com.torishop.product.dto.Product;
import com.torishop.product.dto.UpdateProductRequest;
import com.torishop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    public Product findProductById(Integer productId) {
        ProductEntity entity = repository.findById(productId).orElseThrow(
                () -> new NoSuchElementException("Product doesn't exist " + productId)
        );
        return Product.toProduct(entity);
    }

    public void save(CreateProductRequest request) {
        ProductEntity entity = request.toEntity();
        repository.save(entity);
    }

    public void modify(UpdateProductRequest request) {
        Integer productId = request.getId();
        ProductEntity entity = repository.findById(productId).orElseThrow(
                () -> new NoSuchElementException("Product doesn't exist " + productId)
        );
        entity.setName(request.getName());
        entity.setPrice(request.getPrice());
        entity.setStock(request.getStock());
        entity.setCategory(request.getCategory());
        entity.setDescription(request.getDescription());
        entity.setImage(request.getImage());
        entity.setUpdateDate(LocalDate.now());

        repository.save(entity);
    }
}
