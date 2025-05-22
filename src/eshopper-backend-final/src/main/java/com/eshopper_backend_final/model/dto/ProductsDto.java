package com.eshopper_backend_final.model.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductsDto {
    private Long productId;

    @Size(max = 40, message = "Length value must not exceeded than 40")
    private String productName;

    private SuppliersDto suppliers;

    private CategoryDto category;

    private BigDecimal unitPrice;

    private int unitsInStock;

    private String picture;
}
