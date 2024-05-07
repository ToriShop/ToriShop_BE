package com.torishop.order.dto;

import com.torishop.customer.domain.CustomerEntity;
import com.torishop.order.domain.OrderEntity;
import com.torishop.order.enums.DeliveryStatus;
import com.torishop.orderItem.dto.CreateOrderItemRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
    private String recipientName;
    private String recipientPhone;
    private String recipientAddress;
    private List<CreateOrderItemRequest> orderItems;

    public OrderEntity toEntity(CustomerEntity entity){
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmm");
        int randomNum = ThreadLocalRandom.current().nextInt(10, 100); // 2자리 랜덤 수
        String orderNumber = sdf.format(new Date()) + randomNum;

        return OrderEntity.builder()
                .customerId(entity)
                .recipientName(this.recipientName)
                .recipientPhone(this.recipientPhone)
                .recipientAddress(this.recipientAddress)
                .orderNumber(orderNumber)
                .deliveryStatus(DeliveryStatus.PENDING)
                .build();
    }
}
