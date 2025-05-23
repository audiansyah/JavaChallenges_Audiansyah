package com.eshopper_backend_final.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eshopper_backend_final.model.dto.CartItemsDto;
import com.eshopper_backend_final.service.CartItemsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cart-items")
@Slf4j
@RequiredArgsConstructor
public class CartItemsController {

    private final CartItemsService cartItemsService;

    // Ambil semua cart items berdasarkan cartId
    @GetMapping("/{cartId}")
    public ResponseEntity<List<CartItemsDto>> getByCartId(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartItemsService.findByCartId(cartId));
    }

    // Tambahkan item ke cart
    @PostMapping("/add/")
    public ResponseEntity<CartItemsDto> create(@RequestBody @Valid CartItemsDto dto) {
        return ResponseEntity.ok(cartItemsService.save(dto));
    }

    // Hapus semua cart items berdasarkan cartId
    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteByCartId(@PathVariable Long cartId) {
        cartItemsService.deleteByCartId(cartId);
        return ResponseEntity.noContent().build();
    }
}
