package com.torishop.cart.service.impl;

import com.torishop.cart.CartConverter;
import com.torishop.cart.domain.CartEntity;
import com.torishop.cart.domain.CartId;
import com.torishop.cart.domain.CartRepository;
import com.torishop.cart.dto.Cart;
import com.torishop.cart.dto.CartResponse;
import com.torishop.cart.dto.CreateCartRequest;
import com.torishop.cart.dto.UpdateCartRequest;
import com.torishop.cart.service.CartService;
import com.torishop.customer.domain.CustomerEntity;
import com.torishop.customer.domain.CustomerRepository;
import com.torishop.product.domain.ProductEntity;
import com.torishop.product.domain.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public CartResponse createCart(int customerId, CreateCartRequest request) {
        CartId cartId = makeCartId(customerId, request.getProductId());

        CartEntity cart = CartEntity.builder()
                .cartId(cartId)
                .isInOrder(true)
                .quantity(request.getQuantity())
                .build();
        cartRepository.save(cart);

        return CartResponse.builder()
                .customerId(customerId)
                .productId(request.getProductId())
                .build();
    }

    @Override
    @Transactional
    public List<Cart> getCarts(int customerId){
        List<CartEntity> cartEntities = cartRepository.findByCartIdCustomerEntityId(customerId);
        return cartEntities.stream().map(CartConverter::entityToDto).toList();
    }

    @Override
    @Transactional
    public CartResponse updateCart(int customerId, UpdateCartRequest request) {
        CartId cartId = makeCartId(customerId, request.getProductId());
        CartEntity cartEntity = cartRepository.findById(cartId).get();

        cartEntity.setInOrder(request.isInOrder());
        cartEntity.setQuantity(request.getQuantity());

        return CartResponse.builder()
                .customerId(customerId)
                .productId(request.getProductId())
                .build();
    }

    @Override
    @Transactional
    public CartResponse deleteCart(int customerId, int productId) {
        CartId cartId = makeCartId(customerId, productId);
        cartRepository.deleteById(cartId);

        return CartResponse.builder()
                .customerId(customerId)
                .productId(productId)
                .build();
    }

    public CartId makeCartId(int customerId, int productId){
        CustomerEntity customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        return CartId.builder()
                .customerEntity(customer)
                .productEntity(product)
                .build();
    }
}
