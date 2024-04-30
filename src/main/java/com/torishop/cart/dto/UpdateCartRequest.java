package com.torishop.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCartRequest {
    private int customerId;
    private int productId;
    private boolean isInOrder;
    private int quantity;
}
