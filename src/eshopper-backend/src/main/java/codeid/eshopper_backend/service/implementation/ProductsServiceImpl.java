package codeid.eshopper_backend.service.implementation;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;

import codeid.eshopper_backend.model.dto.ProductsDto;
import codeid.eshopper_backend.model.entity.Category;
import codeid.eshopper_backend.model.entity.Suppliers;
import codeid.eshopper_backend.model.entity.Products;
import codeid.eshopper_backend.repository.CategoryRepository;
import codeid.eshopper_backend.repository.ProductsRepository;
import codeid.eshopper_backend.repository.SuppliersRepository;
import codeid.eshopper_backend.service.ProductsService;

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
            products.getQuantityPerUnit(),
            products.getUnitPrice(),
            products.getUnitsInStock(),
            products.getUnitsOnOrder(),
            products.getReorderLevel(),
            products.getDiscontinued(),
            products.getPicture()
        );
    }

    public static Products mapToEntity(ProductsDto productsDto){
        return new Products(
            productsDto.getProductId(),
            productsDto.getProductName(),
            SuppliersServiceImpl.mapToEntity(productsDto.getSuppliers()),
            CategoryServiceImpl.mapToEntity(productsDto.getCategory()),
            productsDto.getQuantityPerUnit(),
            productsDto.getUnitPrice(),
            productsDto.getUnitsInStock(),
            productsDto.getUnitsOnOrder(),
            productsDto.getReorderLevel(),
            productsDto.getDiscontinued(),
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
    product.setQuantityPerUnit(dto.getQuantityPerUnit());
    product.setUnitPrice(dto.getUnitPrice());
    product.setUnitsInStock(dto.getUnitsInStock());
    product.setUnitsOnOrder(dto.getUnitsOnOrder());
    product.setReorderLevel(dto.getReorderLevel());
    product.setDiscontinued(dto.getDiscontinued());
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
    products.setQuantityPerUnit(entity.getQuantityPerUnit());
    products.setUnitPrice(entity.getUnitPrice());
    products.setUnitsInStock(entity.getUnitsInStock());
    products.setUnitsOnOrder(entity.getUnitsOnOrder());
    products.setReorderLevel(entity.getReorderLevel());
    products.setDiscontinued(entity.getDiscontinued());
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
}
