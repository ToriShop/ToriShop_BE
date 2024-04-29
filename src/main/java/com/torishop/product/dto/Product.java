package com.torishop.product.dto;

import com.torishop.product.domain.ProductEntity;
import org.springframework.beans.factory.annotation.Value;
import com.torishop.product.enums.Category;
import lombok.*;

import java.time.LocalDate;
import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;
    private String name;
    private Integer price;
    private Integer stock;
    private Category category;
    private String description;
    @Value("{default.product.image.url}")
    private String image;
    private LocalDate createDate;
    private LocalDate updateDate;

    public static Product toProduct(ProductEntity entity) {
        return Product.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .category(entity.getCategory())
                .description(entity.getDescription())
                .image(entity.getImage())
                .createDate(entity.getCreateDate())
                .updateDate(entity.getUpdateDate()).build();
    }
}
