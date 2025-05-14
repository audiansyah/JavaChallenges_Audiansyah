package codeid.eshopper_backend.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import codeid.eshopper_backend.model.dto.ProductImageBulkDto;
import codeid.eshopper_backend.model.dto.ProductImageDto;
import codeid.eshopper_backend.model.entity.ProductImage;
import codeid.eshopper_backend.model.entity.Products;
import codeid.eshopper_backend.repository.ProductImageRepository;
import codeid.eshopper_backend.repository.ProductsRepository;
import codeid.eshopper_backend.service.ProductImageService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductsRepository productsRepository;

    public static ProductImageDto mapToDto(ProductImage productImage) {
        return new ProductImageDto(
                productImage.getProductPhotoId(),
                productImage.getFileName(),
                productImage.getFileSize(),
                productImage.getFileType(),
                productImage.getTextUri(),
                productImage.getProducts().getProductId()
        );
    }

    @Override
    public List<ProductImageDto> findAll() {
        log.debug("Request to fetch all product images");
        return this.productImageRepository.findAll()
                .stream()
                .map(ProductImageServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductImageDto findById(Long id) {
        log.debug("Request to fetch product image by id: {}", id);

        return this.productImageRepository.findById(id)
                .map(ProductImageServiceImpl::mapToDto)
                .orElseThrow(() -> new EntityNotFoundException("Product image not found with id " + id));
    }

    @Override
    public ProductImageDto save(ProductImageDto dto) {
        log.debug("Request to create product image: {}", dto);

        Products products = productsRepository.findById(dto.getProductsId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        ProductImage productImage = new ProductImage();
        productImage.setFileName(dto.getFileName());
        productImage.setFileSize(dto.getFileSize());
        productImage.setFileType(dto.getFileType());
        productImage.setTextUri(dto.getTextUri());
        productImage.setProducts(products);

        return mapToDto(productImageRepository.save(productImage));
    }

    @Override
    public ProductImageDto update(Long id, ProductImageDto dto) {
        log.debug("Request to update product image: {}", id);

        var productImage = productImageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product image not found with id " + id));

        Products products = productsRepository.findById(dto.getProductsId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        productImage.setFileName(dto.getFileName());
        productImage.setFileSize(dto.getFileSize());
        productImage.setFileType(dto.getFileType());
        productImage.setTextUri(dto.getTextUri());
        productImage.setProducts(products);

        return mapToDto(productImageRepository.save(productImage));
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete product image: {}", id);

        var productImage = this.productImageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product image not found with id " + id));

        productImageRepository.delete(productImage);
    }

    @Override
    public List<ProductImageDto> findProductImageDtoByProductId(Long productId) {
        log.debug("Request to find product images by productId: {}", productId);

        return productImageRepository.findProductImageByProductIdJpql(productId)
                .stream()
                .map(ProductImageServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductImageDto> bulkInsert(ProductImageBulkDto productImageBulkDto) {
        log.debug("Request to bulk insert product images");

        Products products = productsRepository.findById(productImageBulkDto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productImageBulkDto.getProductId()));


        List<ProductImage> productImages = new ArrayList<>();

        for (ProductImageDto dto : productImageBulkDto.getProductImages()) {
            ProductImage productImage = new ProductImage();
            productImage.setFileName(dto.getFileName());
            productImage.setFileSize(dto.getFileSize());
            productImage.setFileType(dto.getFileType());
            productImage.setTextUri(dto.getTextUri());
            productImage.setProducts(products);
            productImages.add(productImage);
        }

        return productImageRepository.saveAll(productImages)
                .stream()
                .map(ProductImageServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }
}
