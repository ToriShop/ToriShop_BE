package com.torishop.order.service;

import com.torishop.order.dto.CreateOrderRequest;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    void save(CreateOrderRequest request);
}
