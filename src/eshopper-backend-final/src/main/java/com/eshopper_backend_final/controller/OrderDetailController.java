package com.eshopper_backend_final.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eshopper_backend_final.model.dto.OrderDetailDto;
import com.eshopper_backend_final.service.OrderDetailService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/order-detail")
@Slf4j
@RequiredArgsConstructor
public class OrderDetailController{

    private final OrderDetailService orderDetailService;

    // Ambil semua cart items berdasarkan cartId
    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderDetailDto>> getByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderDetailService.findByOrderId(orderId));
    }

    // Tambahkan item ke cart
    @PostMapping("/{orderId}")
    public ResponseEntity<OrderDetailDto> create(@RequestBody @Valid OrderDetailDto dto) {
        return ResponseEntity.ok(orderDetailService.save(dto));
    }

    // Hapus semua cart items berdasarkan cartId
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteByOrderId(@PathVariable Long orderId) {
        orderDetailService.deleteByOrderId(orderId);
        return ResponseEntity.noContent().build();
    }
}
