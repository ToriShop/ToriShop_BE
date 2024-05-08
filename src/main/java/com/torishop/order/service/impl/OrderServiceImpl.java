package com.torishop.order.service.impl;

import com.torishop.customer.domain.CustomerEntity;
import com.torishop.customer.domain.CustomerRepository;
import com.torishop.exception.product.OutOfStockException;
import com.torishop.order.domain.OrderEntity;
import com.torishop.order.domain.OrderRepository;
import com.torishop.order.dto.CreateOrderRequest;
import com.torishop.order.dto.Order;
import com.torishop.order.dto.OrderResponse;
import com.torishop.order.dto.UpdateOrderRequest;
import com.torishop.order.service.OrderService;
import com.torishop.orderItem.domain.OrderItemEmpId;
import com.torishop.orderItem.domain.OrderItemEntity;
import com.torishop.orderItem.domain.OrderItemRepository;
import com.torishop.orderItem.dto.CreateOrderItemRequest;
import com.torishop.product.domain.ProductEntity;
import com.torishop.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
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
    public OrderResponse save(Integer customerId, CreateOrderRequest request) throws OutOfStockException {
        // 고객 ID 확인
        CustomerEntity customerEntity = null;
        if(customerId != 0){
            customerEntity = customerRepository.findById(customerId).orElseThrow(
                    () -> new NoSuchElementException("Customer doesn't exist " + customerId)
            );
        }
        OrderEntity orderEntity = request.toEntity(customerEntity);

        // 총 가격 계산
        Integer totalPrice = request.getOrderItems()
                .stream()
                .map(CreateOrderItemRequest::getPrice)
                .reduce(0, Integer::sum);
        orderEntity.setTotalPrice(totalPrice);
        OrderEntity order = orderRepository.save(orderEntity);

        OrderItemEmpId empId = new OrderItemEmpId();
        empId.setOrderId(order);

        // orderItem 정보 저장
        List<CreateOrderItemRequest> orderItemList = request.getOrderItems();
        orderItemList.forEach(orderItem -> {
            Integer productId = orderItem.getProductId();
            ProductEntity product = productRepository.findById(productId).orElseThrow(
                    () -> new NoSuchElementException("Product doesn't exist " + productId)
            );

            if (product.getStock() < orderItem.getQuantity()) {
                throw new OutOfStockException("Product is out of stock: " + product.getName());
            }

            empId.setProductId(product);
            OrderItemEntity orderItemEntity = orderItem.toEntity(empId);
            orderItemRepository.save(orderItemEntity);

            // product 재고 - 1
            product.setStock(product.getStock() - orderItem.getQuantity());
            productRepository.save(product);
        });

        return OrderResponse.builder()
                .id(order.getId())
                .build();
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

    @Transactional(readOnly = true)
    public List<Order> findOrdersByCustomerId(Integer customerId){
        CustomerEntity entity = customerRepository.findById(customerId).orElseThrow(
                () -> new NoSuchElementException("Customer doesn't exist " + customerId)
        );

        List<Order> orders = orderRepository.findAllByCustomerId(entity).stream().map(
                orderEntity -> orderEntity.toOrder()
        ).collect(Collectors.toList());

        return orders;
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
