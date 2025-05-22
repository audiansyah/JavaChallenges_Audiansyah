package com.eshopper_backend_final.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eshopper_backend_final.model.dto.ProductImageBulkDto;
import com.eshopper_backend_final.model.dto.ProductImageDto;
import com.eshopper_backend_final.model.dto.ProductsDto;
import com.eshopper_backend_final.model.enumeration.EnumStatus;
import com.eshopper_backend_final.model.response.ApiResponse;
import com.eshopper_backend_final.service.BaseCrudService;
import com.eshopper_backend_final.service.FileStorageService;
import com.eshopper_backend_final.service.ProductsService;
import com.eshopper_backend_final.service.ProductImageService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/product")
@Slf4j
@RequiredArgsConstructor
public class ProductsController extends BaseMultipartController<ProductsDto, Long>{
    private final ProductsService productsService;
    private final FileStorageService fileStorageService;
    private final ProductImageService productImageService;

    @Override
    protected BaseCrudService<ProductsDto, Long> getService() {
        return productsService;
    }

    @Override
    public ResponseEntity<List<ProductsDto>> getAll() {
        return super.getAll();
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        return super.delete(id);
    }

    @Override
    public ResponseEntity<ProductsDto> getById(Long id) {
        return super.getById(id);
    }

    @Override
    public ResponseEntity<?> createMultipart(ProductsDto dto, MultipartFile file, String description) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload Product photo");
        }
        try {
            String fileName = fileStorageService.storeFileWithRandomName(file);
            
            dto.setPicture(fileName);
            var productsDto= productsService.save(dto);

            ApiResponse<ProductsDto> response = new ApiResponse<ProductsDto>(
                EnumStatus.Success, "Products created", productsDto);

            return ResponseEntity.ok(response);

            // var response = ApiResponse.builder()
            //     .status(EnumStatus.Succees.toString())
            //     .message("Product created")
            //     .data(ProductsDto)
            //     .build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }

    }

    @Override
    public ResponseEntity<?> viewImage(String fileName) {
        try {
            Resource resource = fileStorageService.loadFile(fileName);
            
            // Cek jika file adalah image
            String contentType = determineContentType(fileName);
            
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                    "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> updateMultipart(Long id, ProductsDto dto, MultipartFile file, String description) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload product picture");
        }

        try {
            String filename = fileStorageService.storeFileWithRandomName(file);
            dto.setPicture(filename);

            ProductsDto productsDto = productsService.update(id, dto);

            ApiResponse<ProductsDto> response = new ApiResponse<>(
                    EnumStatus.Success,
                    "Data updated successfully",
                    productsDto
            );

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError()
                    .body(Collections.singletonMap("error", ex.getMessage()));
        }
    }

    @GetMapping("/{id}/uploadMultipleImages")
    public ResponseEntity<ApiResponse<List<ProductImageDto>>> getAllMultipartBulk(
            @PathVariable Long id
    ) {
        ApiResponse<List<ProductImageDto>> response = new ApiResponse<>(
                EnumStatus.Success,
                "Data retrieved successfully",
                productImageService.findProductImageDtoByProductId(id)
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/{id}/uploadMultipleImages", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<?> createMultipartBulk(
            @PathVariable Long id,
            @RequestPart(value = "files", required = false) MultipartFile[] files) {
                if (files == null || files.length == 0) {
                    return ResponseEntity.badRequest().body("Please upload product images");
                }
                try {
                    List<ProductImageDto> productImageDtos = new ArrayList<>();
                    
                    for (MultipartFile file : files) {
                        String filename = fileStorageService.storeFileWithRandomName(file);
                        ProductImageDto imageDto = new ProductImageDto();
                        imageDto.setFileName(filename);
                        imageDto.setFileSize(file.getSize());
                        imageDto.setFileType(file.getContentType());
                        imageDto.setTextUri("http://localhost:8090/product/view/" + filename);
                        productImageDtos.add(imageDto);
                    }
                    
                    ProductImageBulkDto productImageBulkDto = ProductImageBulkDto.builder()
                    .productId(id)
                    .productImages(productImageDtos)
                    .build();
                    
                    List<ProductImageDto> savedImages = productImageService.bulkInsert(productImageBulkDto);
                    
                    ApiResponse<List<ProductImageDto>> response = new ApiResponse<>(
                        EnumStatus.Success,"Data created successfully",savedImages);
                        return ResponseEntity.ok(response);
                    } catch (Exception ex) {
                        return ResponseEntity.internalServerError()
                        .body(Collections.singletonMap("error", ex.getMessage()));
                    }
                }
                
    @Override
    public ResponseEntity<ProductsDto> create(@Valid ProductsDto entity) {
        return super.create(entity);
    }

    //set pagination
    @GetMapping("/paging")
    public ResponseEntity<?> getAllProducts(Pageable pageable){
        var productDtos = this.productsService.findAll(pageable);
        var response = new ApiResponse<>(EnumStatus.Success, "getAllProductsPaging()", productDtos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getAllProducts(@RequestParam String keyword, Pageable pageable){
        var productDtos = this.productsService.findAll(keyword, pageable);
        var response = new ApiResponse<>(EnumStatus.Success, "getAllProductPaging()", productDtos);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/searchByCate")
    public ResponseEntity<?> getProductsByCate(@RequestParam String keyword, Pageable pageable){
        var productDtos = this.productsService.findAllWithCategory(keyword, pageable);
        var response = new ApiResponse<>(EnumStatus.Success, "getProductsByCate()", productDtos);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/searchByName")
    public ResponseEntity<?> getProductsByName(@RequestParam String keyword){
        var productDtos = this.productsService.findProductsByProductName(keyword);
        var response = new ApiResponse<>(EnumStatus.Success, "getProductsByName()", productDtos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/searchByPrice")
    public ResponseEntity<?> getProductsByPrice(
        @RequestParam(defaultValue = "asc") String sort,
        Pageable pageable
        ) {
            var productDtos = this.productsService.findAllSortedByPrice(sort, pageable);
            var response = new ApiResponse<>(
                EnumStatus.Success,
                "getProductsByPrice() - sort: " + sort,
                productDtos
                );
                return ResponseEntity.ok(response);
            }
    }
