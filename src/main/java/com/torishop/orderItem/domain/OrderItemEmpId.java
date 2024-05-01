package com.torishop.orderItem.domain;

import com.torishop.order.domain.OrderEntity;
import com.torishop.product.domain.ProductEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class OrderItemEmpId implements Serializable {
    // TODO: OrderEntity에 연결
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderId;

    // TODO: ProductEntity에 연결
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productId;
}
