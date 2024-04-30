package com.torishop.cart.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, CartId> {
    List<CartEntity> findByCartIdCustomerEntityId(int customerId);
}
