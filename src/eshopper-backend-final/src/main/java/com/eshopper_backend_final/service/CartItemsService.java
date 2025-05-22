package com.eshopper_backend_final.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.eshopper_backend_final.model.dto.CartItemsDto;
import com.eshopper_backend_final.model.entity.CartItemsId;


public interface CartItemsService extends BaseCrudService<CartItemsDto, CartItemsId>{
    List<CartItemsDto> findAll(Pageable pageable);
    List<CartItemsDto> findByCartId(Long cartId);
    void deleteByCartId(Long cartId);
}
