package com.torishop.order.service;

import com.torishop.order.domain.OrderEntity;
import com.torishop.order.dto.CreateOrderRequest;
import com.torishop.order.dto.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    void save(CreateOrderRequest request);

    List<Order> findAll();
}
