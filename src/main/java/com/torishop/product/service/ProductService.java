package com.torishop.product.service;

import com.torishop.product.dto.CreateProductRequest;
import com.torishop.product.dto.Product;
import com.torishop.product.dto.UpdateProductRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    Product find(Integer productId);

    List<Product> findAll();

    void save(CreateProductRequest request) throws IOException;

    void modify(UpdateProductRequest request) throws IOException;

    void remove(Integer productId);
}
