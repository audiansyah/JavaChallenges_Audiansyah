package com.eshopper_backend_final.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {
    private Long orderId;
    private Long productId;
    private BigDecimal price;
    //ganti int
    private Long quantity;
    private BigDecimal discount;
    private BigDecimal voucher;
}

