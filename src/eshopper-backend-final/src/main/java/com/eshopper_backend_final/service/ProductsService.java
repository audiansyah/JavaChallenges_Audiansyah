package com.eshopper_backend_final.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.eshopper_backend_final.model.dto.ProductsDto;

public interface ProductsService extends BaseCrudService<ProductsDto, Long> {
    List<ProductsDto> findAll(Pageable pageable);
    List<ProductsDto> findAll(String search, Pageable pageable);
    List<ProductsDto> findAllWithCategory(String search, Pageable pageable);
    List<ProductsDto> findProductsByProductName(String search);
    Page<ProductsDto> findAllSortedByPrice(String sortDirection, Pageable pageable);
}
