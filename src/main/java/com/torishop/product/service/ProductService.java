package com.torishop.product.service;

import com.torishop.product.dto.CreateProductRequest;
import com.torishop.product.dto.Product;
import com.torishop.product.dto.UpdateProductRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {

    @Transactional(readOnly = true)
    Product findProductById(Integer productId);

    @Transactional(readOnly = true)
    List<Product> findAll();

    void save(CreateProductRequest request);

    void modify(UpdateProductRequest request);

    void remove(Integer productId);
}
