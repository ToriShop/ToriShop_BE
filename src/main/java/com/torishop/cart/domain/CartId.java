package com.torishop.cart.domain;

import com.torishop.customer.domain.CustomerEntity;
import com.torishop.product.domain.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;

@Data
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartId implements Serializable {
    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;
}
