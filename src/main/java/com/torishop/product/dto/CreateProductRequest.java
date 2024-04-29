package com.torishop.product.dto;

import com.torishop.product.domain.ProductEntity;
import com.torishop.product.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class CreateProductRequest {
    private String name;
    private Integer price;
    private Integer stock;
    private Category category;
    private String description;
    @Value("{default.product.image.url}")
    private String image;

    public ProductEntity toEntity() {
        return ProductEntity.builder()
                .name(this.name)
                .price(this.price)
                .stock(this.stock)
                .category(this.category)
                .description(this.description)
                .image(this.image)
                .build();
    }
}
