package com.torishop.product.service.impl;

import com.torishop.product.domain.ProductEntity;
import com.torishop.product.domain.ProductRepository;
import com.torishop.product.dto.Product;
import com.torishop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

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
}
