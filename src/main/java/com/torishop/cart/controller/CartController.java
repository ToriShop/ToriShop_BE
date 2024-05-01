package com.torishop.cart.controller;

import com.torishop.cart.dto.Cart;
import com.torishop.cart.dto.CartResponse;
import com.torishop.cart.dto.CreateCartRequest;
import com.torishop.cart.dto.UpdateCartRequest;
import com.torishop.cart.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping
    ResponseEntity<CartResponse> createCart(HttpServletRequest httpRequest, @RequestBody CreateCartRequest request){
        int customerId = (int) httpRequest.getAttribute("acId");
        CartResponse cartResponse = cartService.createCart(customerId, request);
        return ResponseEntity.ok(cartResponse);
    }

    @GetMapping
    ResponseEntity<List<Cart>> getCarts(HttpServletRequest httpRequest){
        int customerId = (int) httpRequest.getAttribute("acId");
        List<Cart> carts = cartService.getCarts(customerId);
        return ResponseEntity.ok(carts);
    }

    @PutMapping
    ResponseEntity<CartResponse> updateCart(HttpServletRequest httpRequest, @RequestBody UpdateCartRequest request){
        int customerId = (int) httpRequest.getAttribute("acId");
        CartResponse cartResponse = cartService.updateCart(customerId, request);
        return ResponseEntity.ok(cartResponse);
    }

    @DeleteMapping("/{productId}")
    ResponseEntity<CartResponse> deleteCart(HttpServletRequest httpRequest, @PathVariable("productId") int productId){
        int customerId = (int) httpRequest.getAttribute("acId");
        CartResponse cartResponse = cartService.deleteCart(customerId, productId);
        return ResponseEntity.ok(cartResponse);
    }
}
