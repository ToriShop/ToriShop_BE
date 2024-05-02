package com.torishop.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCartRequest {
    private int productId;
    private boolean isInOrder;
    private int quantity;
}
