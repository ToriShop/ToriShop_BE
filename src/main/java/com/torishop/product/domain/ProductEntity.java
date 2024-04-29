package com.torishop.product.domain;

import com.torishop.product.enums.Category;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Table(name = "product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    @NonNull
    private LocalDate createDate;

    @Column(name = "update_date")
    @NonNull
    private LocalDate updateDate;

    @PrePersist
    private void prePersist() {
        this.createDate = LocalDate.now();
        this.updateDate = LocalDate.now();
    }
}
