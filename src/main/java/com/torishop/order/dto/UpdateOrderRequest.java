package com.torishop.order.dto;

import com.torishop.order.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderRequest {
    private Integer id;
    private String recipientName;
    private String recipientPhone;
    private String recipientAddress;
    private DeliveryStatus deliveryStatus;
}
