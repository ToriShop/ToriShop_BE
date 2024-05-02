package com.torishop.orderItem.dto;

import com.torishop.orderItem.domain.OrderItemEmpId;
import com.torishop.orderItem.domain.OrderItemEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderItemRequest {
    private Integer productId;
    private Integer quantity;
    private Integer price;

    public OrderItemEntity toEntity(OrderItemEmpId empId) {
        return OrderItemEntity.builder()
                .id(empId)
                .price(this.price)
                .quantity(this.quantity)
                .build();
    }
}
