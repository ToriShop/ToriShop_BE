package com.torishop.orderItem.domain;

import com.torishop.order.domain.OrderEntity;
import com.torishop.orderItem.dto.OrderItem;
import com.torishop.product.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, OrderItemEmpId> {

    @Query("SELECT items FROM OrderItemEntity items WHERE items.id.orderId = :order_id")
    List<OrderItemEntity> findByOrderId(@Param("order_id") OrderEntity orderEntity);

    @Query("SELECT items FROM OrderItemEntity items WHERE items.id.productId = :product_id")
    List<OrderItemEntity> findByProductId(@Param("product_id") ProductEntity id);
}
