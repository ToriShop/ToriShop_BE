package com.torishop.order.service;

import com.torishop.order.domain.OrderEntity;
import com.torishop.order.dto.CreateOrderRequest;
import com.torishop.order.dto.Order;
import com.torishop.order.dto.UpdateOrderRequest;
import com.torishop.product.dto.UpdateProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    void save(CreateOrderRequest request);

    List<Order> findAll();

    Order findOrderById(Integer id);

    void modify(UpdateOrderRequest request);
}