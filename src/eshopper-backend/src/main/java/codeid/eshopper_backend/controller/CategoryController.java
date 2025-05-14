package codeid.eshopper_backend.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import codeid.eshopper_backend.model.dto.CategoryDto;
import codeid.eshopper_backend.model.enumeration.EnumStatus;
import codeid.eshopper_backend.model.response.ApiResponse;
import codeid.eshopper_backend.service.BaseCrudService;
import codeid.eshopper_backend.service.CategoryService;
import codeid.eshopper_backend.service.FileStorageService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/category")
@Slf4j
@RequiredArgsConstructor
public class CategoryController extends BaseMultipartController<CategoryDto, Long>{
    private final CategoryService categoryService;
    private final FileStorageService fileStorageService;

    @Override
    protected BaseCrudService<CategoryDto, Long> getService(){
        return categoryService;
    }

    @Override
    public ResponseEntity<List<CategoryDto>> getAll() {
        return super.getAll();
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        return super.delete(id);
    }

    @Override
    public ResponseEntity<CategoryDto> getById(Long id) {
        return super.getById(id);
    }

    @Override
    public ResponseEntity<CategoryDto> update(Long id, @Valid CategoryDto entity) {
        return super.update(id, entity);
    }

    @Override
    public ResponseEntity<?> createMultipart(CategoryDto dto, MultipartFile file, String description) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload catefory photo");
        }

        try {
            String fileName = fileStorageService.storeFileWithRandomName(file);
            
            dto.setPicture(fileName);
            var categoryDto= categoryService.save(dto);

            ApiResponse<CategoryDto> response = new ApiResponse<CategoryDto>(EnumStatus.Success, "Category created", categoryDto);

            return ResponseEntity.ok(response);

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
    public ResponseEntity<?> updateMultipart(Long id, CategoryDto dto, MultipartFile file, String description) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMultipart'");
    }

    @Override
    public ResponseEntity<CategoryDto> create(@Valid CategoryDto entity) {
        return super.create(entity);
    }

}
