package com.torishop.order.domain;

import com.torishop.customer.domain.CustomerEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    @Query("SELECT orders FROM OrderEntity orders WHERE orders.customerId = :customer_id")
    List<OrderEntity> findAllByCustomerId(@Param("customer_id") CustomerEntity customerEntity);
}
