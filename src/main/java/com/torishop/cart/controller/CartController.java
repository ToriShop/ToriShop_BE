package com.torishop.cart.controller;

import com.torishop.cart.dto.Cart;
import com.torishop.cart.dto.CartResponse;
import com.torishop.cart.dto.CreateCartRequest;
import com.torishop.cart.dto.UpdateCartRequest;
import com.torishop.cart.service.CartService;
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
    ResponseEntity<CartResponse> createCart(@RequestBody CreateCartRequest request){
        CartResponse cartResponse = cartService.createCart(request);
        return ResponseEntity.ok(cartResponse);
    }

    @GetMapping("/{customerId}")
    ResponseEntity<List<Cart>> getCarts(@PathVariable("customerId") int customerId){
        List<Cart> carts = cartService.getCarts(customerId);
        return ResponseEntity.ok(carts);
    }

    @PutMapping
    ResponseEntity<CartResponse> updateCart(@RequestBody UpdateCartRequest request){
        CartResponse cartResponse = cartService.updateCart(request);
        return ResponseEntity.ok(cartResponse);
    }

    @DeleteMapping("/{customerId}/{productId}")
    ResponseEntity<CartResponse> deleteCart(@PathVariable("customerId") int customerId,
                                            @PathVariable("productId") int productId){
        CartResponse cartResponse = cartService.deleteCart(customerId, productId);
        return ResponseEntity.ok(cartResponse);
    }
}
