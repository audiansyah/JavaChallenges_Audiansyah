package com.eshopper_backend_final.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.eshopper_backend_final.model.dto.ProductsDto;
import com.eshopper_backend_final.model.entity.Category;
import com.eshopper_backend_final.model.entity.Suppliers;
import com.eshopper_backend_final.model.entity.Products;
import com.eshopper_backend_final.repository.CategoryRepository;
import com.eshopper_backend_final.repository.ProductsRepository;
import com.eshopper_backend_final.repository.SuppliersRepository;
import com.eshopper_backend_final.service.ProductsService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {
    private final ProductsRepository productsRepository;
    private final SuppliersRepository suppliersRepository;
    private final CategoryRepository categoryRepository;


    public static ProductsDto mapToDto(Products products){
        return new ProductsDto(
            products.getProductId(),
            products.getProductName(),
            SuppliersServiceImpl.mapToDto(products.getSuppliers()),
            CategoryServiceImpl.mapToDto(products.getCategory()),
            products.getUnitPrice(),
            products.getUnitsInStock(),
            products.getPicture()
        );
    }

    public static Products mapToEntity(ProductsDto productsDto){
        return new Products(
            productsDto.getProductId(),
            productsDto.getProductName(),
            SuppliersServiceImpl.mapToEntity(productsDto.getSuppliers()),
            CategoryServiceImpl.mapToEntity(productsDto.getCategory()),
            productsDto.getUnitPrice(),
            productsDto.getUnitsInStock(),
            productsDto.getPicture()
        );
    }

    @Override
    public List<ProductsDto> findAll() {
        log.debug("request fetching data product");
        return this.productsRepository.findAll()
                .stream()
                .map(ProductsServiceImpl::mapToDto) 
                .collect(Collectors.toList());
    }

    @Override
    public ProductsDto findById(Long id) {
        log.debug("Request to get product : {}", id);

        return this.productsRepository.findById(id).map(ProductsServiceImpl::mapToDto)
            .orElseThrow(()-> new EntityNotFoundException("product not found with id "+id));
    }

    @Override
    public ProductsDto save(ProductsDto dto) {
    log.debug("Request to create product : {}", dto);

    Suppliers supplier = suppliersRepository.findById(dto.getSuppliers().getSupplierId())
            .orElseThrow(() -> new EntityNotFoundException("Supplier not found"));

    Category category = categoryRepository.findById(dto.getCategory().getCategoryId())
            .orElseThrow(() -> new EntityNotFoundException("Category not found"));

    Products product = new Products();
    product.setProductName(dto.getProductName());
    product.setSuppliers(supplier);
    product.setCategory(category);
    product.setUnitPrice(dto.getUnitPrice());
    product.setUnitsInStock(dto.getUnitsInStock());
    product.setPicture(dto.getPicture());

    return mapToDto(productsRepository.save(product));
}


    @Override
    public ProductsDto update(Long id, ProductsDto entity) {
    log.debug("Request to update product : {}", id);

    var products = this.productsRepository
                        .findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id));

    products.setProductName(entity.getProductName());
    products.setSuppliers(new Suppliers(entity.getSuppliers().getSupplierId(), entity.getSuppliers().getCompanyName()));
    products.setCategory(new Category(entity.getCategory().getCategoryId(), entity.getCategory().getCategoryName(), entity.getCategory().getDescription(), entity.getCategory().getPicture()));
    products.setUnitPrice(entity.getUnitPrice());
    products.setPicture(entity.getPicture());
    
    this.productsRepository.save(products);
    return mapToDto(products);
}

    @Override
    public void delete(Long id) {
        log.debug("Request to delete product : {}", id);

        var products = this.productsRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot find categories with id " + id)); 

        this.productsRepository.delete(products);
    }

    @Override
    public List<ProductsDto> findAll(Pageable pageable) {
        return this.productsRepository.findAll(pageable).stream().map(ProductsServiceImpl::mapToDto).toList();
    }

    @Override
    public List<ProductsDto> findAll(String search, Pageable pageable) {
        return this.productsRepository.findByProductNameContainingIgnoreCase(search, pageable)
                .stream().map(ProductsServiceImpl::mapToDto).toList();
    }

    @Override
    public List<ProductsDto> findAllWithCategory(String search, Pageable pageable) {
        return this.productsRepository.findByCategoryCategoryNameContainingIgnoreCase(search, pageable)
                .stream().map(ProductsServiceImpl::mapToDto).toList();
    }

    @Override
    public List<ProductsDto> findProductsByProductName(String search) {
        return this.productsRepository.findProductsByProductNameLike(search)
                .stream().map(ProductsServiceImpl::mapToDto).toList();
    }

    @Override
    public Page<ProductsDto> findAllSortedByPrice(String sortDirection, Pageable pageable) {
        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable sortedPageable = PageRequest.of(
            pageable.getPageNumber(),
            pageable.getPageSize(),
            Sort.by(direction, "unitPrice")
            );
            
            return this.productsRepository.findAll(sortedPageable).map(ProductsServiceImpl::mapToDto);
        }
    }
