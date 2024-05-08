package com.torishop.cart.service;

import com.torishop.cart.dto.Cart;
import com.torishop.cart.dto.CartResponse;
import com.torishop.cart.dto.CreateCartRequest;
import com.torishop.cart.dto.UpdateCartRequest;

import java.net.MalformedURLException;
import java.util.List;

public interface CartService {
    CartResponse createCart(int customerId, CreateCartRequest request);
    List<Cart> getCarts(int customerId) throws MalformedURLException;
    CartResponse updateCart(int customerId, UpdateCartRequest request);
    CartResponse deleteCart(int customerId, int productId);
}
