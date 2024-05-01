package com.torishop.order.controller;

import com.torishop.order.dto.CreateOrderRequest;
import com.torishop.order.dto.Order;
import com.torishop.order.dto.UpdateOrderRequest;
import com.torishop.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOne(@PathVariable Integer id) {
        try {
            Order order = orderService.findOrderById(id);

            return ResponseEntity.ok(order);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

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

    @PutMapping
    public ResponseEntity<?> updateOne(@RequestBody UpdateOrderRequest request) {
        try {
            orderService.modify(request);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Integer id) {
        try {
            orderService.remove(id);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
