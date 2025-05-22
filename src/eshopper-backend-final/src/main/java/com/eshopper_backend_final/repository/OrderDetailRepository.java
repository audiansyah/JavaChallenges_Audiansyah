package com.eshopper_backend_final.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eshopper_backend_final.model.entity.OrderDetail;
import com.eshopper_backend_final.model.entity.OrderDetailId;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
List<OrderDetail> findByOrders_OrderId(Long orderId);
}
