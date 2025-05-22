package com.eshopper_backend_final.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImageBulkDto {
    private Long productId;
    private List<ProductImageDto> productImages;
}
