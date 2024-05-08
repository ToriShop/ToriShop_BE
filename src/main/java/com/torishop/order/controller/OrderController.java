package com.torishop.order.controller;

import com.torishop.exception.product.OutOfStockException;
import com.torishop.exception.dto.ErrorResponse;
import com.torishop.order.dto.CreateOrderRequest;
import com.torishop.order.dto.Order;
import com.torishop.order.dto.OrderResponse;
import com.torishop.order.dto.UpdateOrderRequest;
import com.torishop.order.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

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

    @GetMapping("/customer")
    public ResponseEntity<List<Order>> getAllByCustomerId(HttpServletRequest httpRequest) {
        try {
            int id = (int) httpRequest.getAttribute("acId");
            List<Order> orders = orderService.findOrdersByCustomerId(id);
            return ResponseEntity.ok(orders);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createOne(HttpServletRequest httpRequest, @RequestBody CreateOrderRequest request) {
        int id = 0;
        OrderResponse orderResponse;
        if(httpRequest.getAttribute("acId") != null){
            id = (int) httpRequest.getAttribute("acId");
        }
        try {
            orderResponse = orderService.save(id, request);
        } catch (OutOfStockException exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(exception.getMessage()));
        }

        return ResponseEntity.ok(orderResponse);
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
