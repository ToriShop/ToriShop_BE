package com.torishop.orderItem.domain;

import com.torishop.order.domain.OrderEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Table(name = "orderItem")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quantity")
    @ColumnDefault("1")
    private Integer quantity;

    @Column(name = "price")
    @ColumnDefault("0")
    private Integer price;

    // TODO: OrderEntity에 연결
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderId;

    // TODO: ProductEntity에 연결
    @Column(name = "product_id")
    private Integer productId;
}
