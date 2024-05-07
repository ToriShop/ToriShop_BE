package com.torishop.product.domain;

import com.torishop.product.dto.Product;
import com.torishop.product.enums.Category;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

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

    @Column(name = "name", length = 50)
    @NonNull
    private String name;

    @Column(name = "price")
    @NonNull
    private Integer price = 0;

    @Column(name = "stock")
    @NonNull
    private Integer stock = 0;

    @Column(name = "category", length = 20)
    @Enumerated(EnumType.STRING)
    @NonNull
    private Category category;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "image", length = 100)
    private String image;

    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDate createDate;

    @Column(name = "update_date")
    @CreationTimestamp
    private LocalDate updateDate;

    public Product toProduct() {
        return Product.builder()
                .id(this.id)
                .name(this.name)
                .price(this.price)
                .stock(this.stock)
                .category(this.category)
                .description(this.description)
                .image(this.image)
                .createDate(this.createDate)
                .updateDate(this.updateDate).build();
    }
}
