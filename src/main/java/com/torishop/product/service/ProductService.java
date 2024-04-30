package com.torishop.product.service;

import com.torishop.product.dto.CreateProductRequest;
import com.torishop.product.dto.Product;
import com.torishop.product.dto.UpdateProductRequest;

import java.util.List;

public interface ProductService {

    Product find(Integer productId);

    List<Product> findAll();

    void save(CreateProductRequest request);

    void modify(UpdateProductRequest request);

    void remove(Integer productId);
}
