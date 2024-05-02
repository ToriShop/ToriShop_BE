package com.torishop.orderItem.service.impl;

import com.torishop.order.domain.OrderEntity;
import com.torishop.order.domain.OrderRepository;
import com.torishop.orderItem.domain.OrderItemRepository;
import com.torishop.orderItem.dto.OrderItem;
import com.torishop.orderItem.service.OrderItemService;
import com.torishop.product.domain.ProductEntity;
import com.torishop.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    public final OrderRepository orderRepository;
    public final ProductRepository productRepository;
    public final OrderItemRepository orderItemRepository;

    public List<OrderItem> findByOrderId(Integer id) {
        OrderEntity entity = orderRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Order doesn't exist " + id)
        );

        List<OrderItem> orderItems = orderItemRepository.findByOrderId(entity).stream().map(
                orderItemEntity -> orderItemEntity.toOrderItem()
        ).collect(Collectors.toList());

        return orderItems;
    }

    public List<OrderItem> findByProductId(Integer id){
        ProductEntity entity = productRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Order doesn't exist " + id)
        );

        List<OrderItem> orderItems = orderItemRepository.findByProductId(entity).stream().map(
                orderItemEntity -> orderItemEntity.toOrderItem()
        ).collect(Collectors.toList());

        return orderItems;
    }

}
