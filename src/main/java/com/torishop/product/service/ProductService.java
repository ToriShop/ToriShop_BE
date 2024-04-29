package com.torishop.product.service;

import com.torishop.product.domain.ProductEntity;
import com.torishop.product.dto.CreateProductRequest;
import com.torishop.product.dto.Product;
import com.torishop.product.dto.UpdateProductRequest;
import org.springframework.transaction.annotation.Transactional;

public interface ProductService {

    @Transactional(readOnly = true)
    Product findProductById(Integer productId);

    void save(CreateProductRequest request);

    void modify(UpdateProductRequest request);
}
