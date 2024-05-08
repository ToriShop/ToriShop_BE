package com.torishop.order.dto;

import com.torishop.order.enums.DeliveryStatus;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer id;
    private Integer customerId;
    private String orderNumber;
    private Integer totalPrice;
    private String recipientName;
    private String recipientPhone;
    private String recipientAddress;
    private DeliveryStatus deliveryStatus;
    private LocalDate orderDate;
}
