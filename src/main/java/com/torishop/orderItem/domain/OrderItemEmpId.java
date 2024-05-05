package com.torishop.orderItem.domain;

import com.torishop.order.domain.OrderEntity;
import com.torishop.product.domain.ProductEntity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class OrderItemEmpId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productId;
}
