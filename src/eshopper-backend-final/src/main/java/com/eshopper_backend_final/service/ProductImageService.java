package com.eshopper_backend_final.service;

import java.util.List;

import com.eshopper_backend_final.model.dto.ProductImageBulkDto;
import com.eshopper_backend_final.model.dto.ProductImageDto;

public interface ProductImageService extends BaseCrudService<ProductImageDto, Long>{
    List<ProductImageDto> findProductImageDtoByProductId(Long id);
    List<ProductImageDto> bulkInsert(ProductImageBulkDto productImageBulkDto);
}
