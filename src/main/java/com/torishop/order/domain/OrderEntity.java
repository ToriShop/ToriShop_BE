package com.torishop.order.domain;

import com.torishop.order.dto.Order;
import com.torishop.customer.domain.CustomerEntity;
import com.torishop.order.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.aspectj.weaver.ast.Or;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;

@Entity
@Table(name = "_order")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // TODO: CustomerEntity와 연결
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerId;

    @Column(name = "order_number", length = 10)
    @NonNull
    private String orderNumber;

    @Column(name = "total_price")
    @NotNull
    private Integer totalPrice;

    @Column(name = "recipient_name", length = 10)
    @NonNull
    private String recipientName;

    @Column(name = "recipient_phone", length = 15)
    @NotNull
    private String recipientPhone;

    @Column(name = "recipient_address", length = 50)
    @NonNull
    private String recipientAddress;

    @Column(name = "delivery_status", length = 10)
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Column(name = "order_date")
    @CreationTimestamp
    private LocalDate orderDate;

    public Order toOrder() {
        return Order.builder()
                .id(this.id)
                .customerId(this.customerId.getId())
                .orderNumber(this.orderNumber)
                .totalPrice(this.totalPrice)
                .recipientName(this.recipientName)
                .recipientPhone(this.recipientPhone)
                .recipientAddress(this.recipientAddress)
                .deliveryStatus(this.deliveryStatus)
                .orderDate(this.orderDate)
                .build();
    }
}
