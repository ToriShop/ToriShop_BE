package com.torishop.order.service;

import com.torishop.order.dto.CreateOrderRequest;
import com.torishop.order.dto.Order;
import com.torishop.order.dto.UpdateOrderRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    void save(Integer customerId, CreateOrderRequest request);

    List<Order> findAll();

    Order findOrderById(Integer id);

    List<Order> findOrdersByCustomerId(Integer customerId);

    void modify(UpdateOrderRequest request);

    void remove(Integer id);
}
