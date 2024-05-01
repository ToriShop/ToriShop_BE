package com.torishop.order.controller;

import com.torishop.order.dto.CreateOrderRequest;
import com.torishop.order.dto.Order;
import com.torishop.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAll() {
        List<Order> orders = orderService.findAll();

        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<?> createOne(@RequestBody CreateOrderRequest request) {
        orderService.save(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
