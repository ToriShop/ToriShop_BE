package com.torishop.product;

import com.torishop.product.domain.ProductEntity;
import com.torishop.product.dto.Product;
import lombok.experimental.UtilityClass;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@UtilityClass
public class ProductConverter {
    public Product entityToDto(ProductEntity productEntity) {
        String image = new String(productEntity.getImage());
        Path imagePath = Paths.get(image);
        Resource imageResource = null;
        try {
            imageResource = new UrlResource(imagePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .stock(productEntity.getStock())
                .category(productEntity.getCategory())
                .description(productEntity.getDescription())
                .image(Arrays.toString(productEntity.getImage()))
                .createDate(productEntity.getCreateDate())
                .updateDate(productEntity.getUpdateDate())
                .build();
    }
}
