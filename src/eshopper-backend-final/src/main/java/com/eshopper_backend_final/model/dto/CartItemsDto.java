package com.eshopper_backend_final.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemsDto {
    private Long cartId;
    private Long productId;
    private Long quantity;
    private BigDecimal unitPrice;
    private BigDecimal discount;
}
