package com.torishop.product.domain;

import com.torishop.product.dto.Product;
import com.torishop.product.enums.Category;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@Entity
@Table(name = "product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "category", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDate createDate;

    @Column(name = "update_date")
    @CreationTimestamp
    private LocalDate updateDate;

    public Product toProduct() {

        String image = new String(this.image);
        Path imagePath = Paths.get(image);
        Resource imageResource = null;
        try {
            imageResource = new UrlResource(imagePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        Product product = Product.builder()
                .id(this.id)
                .name(this.name)
                .price(this.price)
                .stock(this.stock)
                .category(this.category)
                .description(this.description)
                .image(new String(this.image))
                .createDate(this.createDate)
                .updateDate(this.updateDate)
                .build();

        return product;
    }
}
