package com.eshopper_backend_final.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.eshopper_backend_final.model.dto.OrderDetailDto;
import com.eshopper_backend_final.model.entity.OrderDetailId;

public interface OrderDetailService extends BaseCrudService<OrderDetailDto, OrderDetailId>{
    List<OrderDetailDto> findAll(Pageable pageable);
    List<OrderDetailDto> findByOrderId(Long orderId);
    void deleteByOrderId(Long orderId);
}
