package com.torishop.orderItem.dto;

import com.torishop.order.dto.Order;
import com.torishop.orderItem.domain.OrderItemEmpId;
import com.torishop.orderItem.domain.OrderItemEntity;
import com.torishop.product.dto.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private Order orderId;
    private Product productId;
    private Integer quantity;
    // 프론트에서 넘겨진 값 기준으로 설정한다.
    private Integer price;

    public OrderItemEntity toEntity(OrderItemEmpId empId) {
        return OrderItemEntity.builder()
                .id(empId)
                .price(this.price)
                .quantity(this.quantity)
                .build();
    }
}
