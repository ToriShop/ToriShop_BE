package com.torishop.product.dto;

import com.torishop.product.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequest {
    private Integer id;
    private String name;
    private Integer price;
    private Integer stock;
    private Category category;
    private String description;
    private MultipartFile image;
}
