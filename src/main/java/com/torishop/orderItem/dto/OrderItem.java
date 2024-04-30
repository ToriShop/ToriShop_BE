package com.torishop.orderItem.dto;

import com.torishop.order.domain.OrderEntity;
import com.torishop.orderItem.domain.OrderItemEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private Integer productId;
    private Integer quantity;
    // 프론트에서 넘겨진 값 기준으로 설정한다.
    private Integer price;

    public OrderItemEntity toEntity(OrderEntity orderEntity) {
        return OrderItemEntity.builder()
                .orderId(orderEntity)
                .productId(this.productId)
                .price(this.price)
                .quantity(this.quantity)
                .build();
    }
}
