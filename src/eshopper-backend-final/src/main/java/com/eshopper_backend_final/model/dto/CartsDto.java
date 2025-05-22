package com.eshopper_backend_final.model.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartsDto {
    private Long cartId;
    private Long userId;
    private List<CartItemsDto> cartItems;
}
