package com.torishop.product.dto;

import com.torishop.product.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
@Builder
@AllArgsConstructor
public class UpdateProductRequest {
    private Integer id;
    private String name;
    private Integer price;
    private Integer stock;
    private Category category;
    private String description;
    @Value("{default.product.image.url}")
    private String image;
}
