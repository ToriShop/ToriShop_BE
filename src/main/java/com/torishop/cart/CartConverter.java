package com.torishop.cart;

import com.torishop.cart.domain.CartEntity;
import com.torishop.cart.dto.Cart;
import com.torishop.customer.CustomerConverter;
import com.torishop.product.ProductConverter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CartConverter {
    public Cart entityToDto(CartEntity cartEntity){
        return Cart.builder()
                .customer(CustomerConverter.entityToDto(cartEntity.getCartId().getCustomerEntity()))
                .product(ProductConverter.entityToDto(cartEntity.getCartId().getProductEntity()))
                .isInOrder(cartEntity.isInOrder())
                .quantity(cartEntity.getQuantity())
                .build();
    }
}
