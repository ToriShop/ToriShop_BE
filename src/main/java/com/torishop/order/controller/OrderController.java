package com.torishop.order.controller;

import com.torishop.order.dto.CreateOrderRequest;
import com.torishop.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOne(@RequestBody CreateOrderRequest request) {
        orderService.save(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
