package com.eshopper_backend_final.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshopper_backend_final.model.dto.OrdersDto;
import com.eshopper_backend_final.service.BaseCrudService;
import com.eshopper_backend_final.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/order")
@Slf4j
@RequiredArgsConstructor
public class OrderController extends BaseCrudController<OrdersDto, Long>{
    
    private final OrderService orderService;

    @Override
    protected BaseCrudService<OrdersDto, Long> getService() {
        return orderService;
    }

    @Override
    public ResponseEntity<List<OrdersDto>> getAll() {
        return super.getAll();
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        return super.delete(id);
    }

    @Override
    public ResponseEntity<OrdersDto> getById(Long id) {
        return super.getById(id);
    }

    @Override
    @PostMapping("/checkout/")
    public ResponseEntity<OrdersDto> create(@Valid OrdersDto entity) {
        return super.create(entity);
    }
}
