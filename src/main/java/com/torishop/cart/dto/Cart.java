package com.torishop.cart.dto;

import com.torishop.customer.dto.Customer;
import com.torishop.product.dto.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private Customer customer;
    private Product product;
    private boolean isInOrder;
    private int quantity;
}
