package com.torishop.orderItem.domain;

import com.torishop.orderItem.dto.OrderItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "orderItem")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntity {
    @EmbeddedId
    private OrderItemEmpId id;

    @Column(name = "quantity")
    @ColumnDefault("1")
    private Integer quantity;

    @Column(name = "price")
    @ColumnDefault("0")
    private Integer price;

    public OrderItem toOrderItem() {
        return OrderItem.builder()
                .orderId(this.id.getOrderId().getId())
                .productId(this.id.getProductId().getId())
                .price(this.price)
                .quantity(this.quantity)
                .build();
    }
}
