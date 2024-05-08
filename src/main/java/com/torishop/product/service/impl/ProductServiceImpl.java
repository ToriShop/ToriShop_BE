package com.torishop.product.service.impl;

import com.torishop.product.domain.ProductEntity;
import com.torishop.product.domain.ProductRepository;
import com.torishop.product.dto.CreateProductRequest;
import com.torishop.product.dto.Product;
import com.torishop.product.dto.UpdateProductRequest;
import com.torishop.product.service.ProductService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final String IMAGE_DIRECTORY = "C:\\Users\\LG\\Desktop";
    @Transactional(readOnly = true)
    public Product find(Integer productId) {
        ProductEntity entity = repository.findById(productId).orElseThrow(
                () -> new NoSuchElementException("Product doesn't exist " + productId)
        );
        return entity.toProduct();
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        List<ProductEntity> entityList = repository.findAll();
        List<Product> productList = entityList.stream().map(
                entity -> entity.toProduct()
        ).collect(Collectors.toList());

        return productList;
    }

    public void save(CreateProductRequest request) throws IOException {
        ProductEntity entity = ProductEntity.builder()
                .name(request.getName())
                .price(request.getPrice())
                .stock(request.getStock())
                .category(request.getCategory())
                .description(request.getDescription())
                .build();

        String newImagePath = "";
        if(request.getImage()!=null && !request.getImage().isEmpty()){
            newImagePath = saveImage(request.getImage());
        }
        entity.setImage(newImagePath.getBytes());
        System.out.println(newImagePath);

        repository.save(entity);
    }

    @Transactional
    public void modify(UpdateProductRequest request) throws IOException {
        Integer productId = request.getId();
        ProductEntity entity = repository.findById(productId).orElseThrow(
                () -> new NoSuchElementException("Product doesn't exist " + productId)
        );
        entity.setName(request.getName());
        entity.setPrice(request.getPrice());
        entity.setStock(request.getStock());
        entity.setCategory(request.getCategory());
        entity.setDescription(request.getDescription());

        String newImagePath = "";
        if(request.getImage()!=null && !request.getImage().isEmpty()){
            newImagePath = saveImage(request.getImage());
        }
        entity.setImage(newImagePath.getBytes());
        System.out.println(newImagePath);
        System.out.println("+++++++++++++++++++++++++++");
        System.out.println("저장될 값: "+newImagePath.getBytes());
        System.out.println("저장된 값: "+entity.getImage());

        entity.setUpdateDate(LocalDate.now());
    }
    private String saveImage(MultipartFile image) throws IOException {
        // 파일 저장 경로 생성
        String originalFilename = image.getOriginalFilename();
        String uniqueFilename = java.util.UUID.randomUUID().toString() + "_" + originalFilename;
        Path filePath = Paths.get(IMAGE_DIRECTORY, uniqueFilename);


        // 디렉토리가 존재하지 않는 경우 생성
        Files.createDirectories(filePath.getParent());

        try {
            Files.copy(image.getInputStream(), filePath);
        } catch (IOException e) {
            // 이미지 저장 실패 시 예외 처리
            // 로그를 남기고, 필요한 경우 사용자에게 알림
            System.err.println("Failed to save image: " + e.getMessage());
            throw e;
        }

        // 파일 경로를 문자열로 반환
        return filePath.toString();
    }
    public void remove(Integer productId) {
        ProductEntity entity = repository.findById(productId).orElseThrow(
                () -> new NoSuchElementException("Product doesn't exist " + productId)
        );

        repository.delete(entity);
    }
}
