package com.torishop.product;

import com.torishop.product.domain.ProductEntity;
import com.torishop.product.dto.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductConverter {
    public Product entityToDto(ProductEntity productEntity){
        return Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .stock(productEntity.getStock())
                .category(productEntity.getCategory())
                .description(productEntity.getDescription())
                .image(productEntity.getImage())
                .createDate(productEntity.getCreateDate())
                .updateDate(productEntity.getUpdateDate())
                .build();
    }
}
