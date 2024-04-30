package com.torishop.order.service.impl;

import com.torishop.order.domain.OrderEntity;
import com.torishop.order.domain.OrderRepository;
import com.torishop.order.dto.CreateOrderRequest;
import com.torishop.order.service.OrderService;
import com.torishop.orderItem.domain.OrderItemEmpId;
import com.torishop.orderItem.domain.OrderItemEntity;
import com.torishop.orderItem.domain.OrderItemRepository;
import com.torishop.orderItem.dto.OrderItem;
import com.torishop.product.domain.ProductEntity;
import com.torishop.product.domain.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

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

        OrderItemEmpId empId = new OrderItemEmpId();
        empId.setOrderId(order);

        // orderItem 정보 저장
        List<OrderItem> orderItemList = request.getOrderItems();
        orderItemList.forEach(orderItem -> {
            Integer productId = orderItem.getProductId();
            ProductEntity product = productRepository.findById(productId).orElseThrow(
                    () -> new NoSuchElementException("Product doesn't exist " + productId)
            );
            empId.setProductId(product);
            OrderItemEntity orderItemEntity = orderItem.toEntity(empId);
            orderItemRepository.save(orderItemEntity);
        });
    }
}
