package com.torishop.order.dto;

import com.torishop.order.domain.OrderEntity;
import com.torishop.order.enums.DeliveryStatus;
import com.torishop.orderItem.dto.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.Order;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
    private Integer customerId;
    private String recipientName;
    private String recipientPhone;
    private String recipientAddress;
    private List<OrderItem> orderItems;

    public OrderEntity toEntity(){
        return OrderEntity.builder()
                .customerId(this.customerId)
                .recipientName(this.recipientName)
                .recipientPhone(this.recipientPhone)
                .recipientAddress(this.recipientAddress)
                .deliveryStatus(DeliveryStatus.PENDING)
                .build();
    }
}
