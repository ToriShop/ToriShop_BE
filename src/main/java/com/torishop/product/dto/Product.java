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
    private String image;
    private LocalDate createDate;
    private LocalDate updateDate;
}
