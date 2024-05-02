package com.torishop.cart.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "cart")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartEntity {
    @Id
    @Embedded
    private CartId cartId;

    @Column(name = "is_in_order")
    @NotNull
    private boolean isInOrder;

    @Column(name = "quantity")
    @NotNull
    private int quantity;
}
