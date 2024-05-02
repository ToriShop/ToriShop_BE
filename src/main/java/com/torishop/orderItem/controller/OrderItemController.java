package com.torishop.orderItem.controller;

import com.torishop.orderItem.domain.OrderItemRepository;
import com.torishop.orderItem.dto.OrderItem;
import com.torishop.orderItem.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orderItem")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping("/order/{id}")
    public ResponseEntity<List<OrderItem>> getAllByOrderId(@PathVariable Integer id) {
        try {
            List<OrderItem> orderItems = orderItemService.findByOrderId(id);
            return ResponseEntity.ok(orderItems);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<List<OrderItem>>  getAllByProductId(@PathVariable Integer id) {
        try {
            List<OrderItem> orderItems = orderItemService.findByProductId(id);
            return ResponseEntity.ok(orderItems);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
