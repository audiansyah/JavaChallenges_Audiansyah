package codeid.eshopper_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import codeid.eshopper_backend.model.dto.CategoryDto;
import codeid.eshopper_backend.service.BaseCrudService;
import codeid.eshopper_backend.service.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/category")
@Slf4j
@RequiredArgsConstructor
public class CategoryController extends BaseCrudController<CategoryDto, Long>{
    private final CategoryService categoryService;

    @Override
    protected BaseCrudService<CategoryDto, Long> getService(){
        return categoryService;
    }

    @Override
    public ResponseEntity<CategoryDto> create(@Valid CategoryDto entity) {
        return super.create(entity);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        return super.delete(id);
    }

    @Override
    public ResponseEntity<List<CategoryDto>> getAll() {
        return super.getAll();
    }

    @Override
    public ResponseEntity<CategoryDto> getById(Long id) {
        return super.getById(id);
    }

    @Override
    public ResponseEntity<CategoryDto> update(Long id, @Valid CategoryDto entity) {
        return super.update(id, entity);
    }

}
