package com.torishop.orderItem.service;

import com.torishop.orderItem.domain.OrderItemEntity;
import com.torishop.orderItem.dto.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> findByOrderId(Integer id);
    List<OrderItem> findByProductId(Integer id);
}
