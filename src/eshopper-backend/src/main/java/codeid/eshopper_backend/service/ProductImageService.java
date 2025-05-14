package codeid.eshopper_backend.service;

import java.util.List;

import codeid.eshopper_backend.model.dto.ProductImageBulkDto;
import codeid.eshopper_backend.model.dto.ProductImageDto;

public interface ProductImageService extends BaseCrudService<ProductImageDto, Long>{
    List<ProductImageDto> findProductImageDtoByProductId(Long id);
    List<ProductImageDto> bulkInsert(ProductImageBulkDto productImageBulkDto);
}
