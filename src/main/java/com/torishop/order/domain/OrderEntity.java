package com.torishop.order.domain;

import com.torishop.order.enums.DeliveryStatus;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;

@Entity
@Table(name = "order")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // TODO: CustomerEntity와 연결
    @Column(name = "customer_id")
    @NonNull
    private Integer customerId;

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
}