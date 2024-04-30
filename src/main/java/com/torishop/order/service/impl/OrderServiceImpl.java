package com.torishop.order.service.impl;

import com.torishop.order.domain.OrderEntity;
import com.torishop.order.domain.OrderRepository;
import com.torishop.order.dto.CreateOrderRequest;
import com.torishop.order.service.OrderService;
import com.torishop.orderItem.domain.OrderItemEntity;
import com.torishop.orderItem.domain.OrderItemRepository;
import com.torishop.orderItem.dto.OrderItem;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public void save(CreateOrderRequest request) {
        OrderEntity orderEntity = request.toEntity();

        // 주문 번호 할당
        String orderNumber = UUID.randomUUID().toString();
        orderEntity.setOrderNumber(orderNumber);

        // 총 가격 계산
        Integer totalPrice = request.getOrderItems()
                .stream()
                .map(OrderItem::getPrice)
                .reduce(0, Integer::sum);
        orderEntity.setTotalPrice(totalPrice);
        OrderEntity order = orderRepository.save(orderEntity);

        // orderItem 정보 저장
        List<OrderItem> orderItemList = request.getOrderItems();
        orderItemList.forEach(orderItem -> {
            OrderItemEntity orderItemEntity = orderItem.toEntity(order);
            orderItemRepository.save(orderItemEntity);
        });
    }
}
