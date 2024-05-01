package com.torishop.order.service.impl;

import com.torishop.customer.domain.CustomerEntity;
import com.torishop.customer.domain.CustomerRepository;
import com.torishop.order.domain.OrderEntity;
import com.torishop.order.domain.OrderRepository;
import com.torishop.order.dto.CreateOrderRequest;
import com.torishop.order.dto.Order;
import com.torishop.order.dto.UpdateOrderRequest;
import com.torishop.order.service.OrderService;
import com.torishop.orderItem.domain.OrderItemEmpId;
import com.torishop.orderItem.domain.OrderItemEntity;
import com.torishop.orderItem.domain.OrderItemRepository;
import com.torishop.orderItem.dto.OrderItem;
import com.torishop.product.domain.ProductEntity;
import com.torishop.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public void save(CreateOrderRequest request) {
        // 고객 ID 확인
        Integer customerId = request.getCustomerId();
        CustomerEntity customerEntity = customerRepository.findById(customerId).orElseThrow(
                () -> new NoSuchElementException("Customer doesn't exist " + customerId)
        );
        OrderEntity orderEntity = request.toEntity(customerEntity);

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

            // product 재고 - 1
            product.setStock(product.getStock() - 1);
            productRepository.save(product);
        });
    }


    @Transactional(readOnly = true)
    public List<Order> findAll() {
        List<OrderEntity> entities = orderRepository.findAll();
        List<Order> orders = entities.stream().map(
                orderEntity -> orderEntity.toOrder()
        ).collect(Collectors.toList());

        return orders;
    }

    @Transactional(readOnly = true)
    public Order findOrderById(Integer id) {
        OrderEntity entity = orderRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Order doesn't exist " + id)
        );

        return entity.toOrder();
    }

    @Transactional
    public void modify(UpdateOrderRequest request){
        Integer orderId = request.getId();
        OrderEntity entity = orderRepository.findById(orderId).orElseThrow(
                () -> new NoSuchElementException("Order doesn't exist " + orderId)
        );

        entity.setRecipientName(request.getRecipientName());
        entity.setRecipientPhone(request.getRecipientPhone());
        entity.setRecipientAddress(request.getRecipientAddress());
        entity.setDeliveryStatus(request.getDeliveryStatus());
    }

    @Transactional
    public void remove(Integer id){
        OrderEntity entity = orderRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Order doesn't exist " + id)
        );

        orderRepository.delete(entity);
    }
}
