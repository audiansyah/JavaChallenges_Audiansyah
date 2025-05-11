package codeid.eshopper_backend.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import codeid.eshopper_backend.model.dto.CategoryDto;
import codeid.eshopper_backend.repository.CategoryRepository;
import codeid.eshopper_backend.service.CategoryService;
import codeid.eshopper_backend.model.entity.Category;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public static CategoryDto mapToDto(Category category){
        return new CategoryDto(
            category.getCategoryId(),
            category.getCategoryName(),
            category.getDescription(),
            category.getPicture()
        );
    }

    public static Category mapToEntity(CategoryDto categoryDto){
        return new Category(
            categoryDto.getCategoryId(),
            categoryDto.getCategoryName(),
            categoryDto.getDescription(),
            categoryDto.getPicture()
        );
    }

    @Override
    public List<CategoryDto> findAll() {
        log.debug("request fetching data categories");
        return this.categoryRepository.findAll()
                .stream()
                .map(CategoryServiceImpl::mapToDto) // untuk ubah tipe data dari department ke departmentDto
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Long id) {
        log.debug("Request to get categories : {}", id);

        return this.categoryRepository.findById(id).map(CategoryServiceImpl::mapToDto)
            .orElseThrow(()-> new EntityNotFoundException("categories not found with id "+id));
    }

    @Override
    public CategoryDto save(CategoryDto entity) {
        log.debug("Request to create categories : {}", entity);
        Category category = new Category();
    category.setCategoryName(entity.getCategoryName());
    category.setDescription(entity.getDescription());

    return mapToDto(this.categoryRepository.save(category));
    }

    @Override
    public CategoryDto update(Long id, CategoryDto entity) {
    log.debug("Request to update categories : {}", id);

    var category = this.categoryRepository
                        .findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id));

    category.setCategoryName(entity.getCategoryName());
    category.setDescription(entity.getDescription());
    category.setPicture(entity.getPicture());
    
    this.categoryRepository.save(category);
    return mapToDto(category);
}

    @Override
    public void delete(Long id) {
        log.debug("Request to delete categories : {}", id);

        var category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot find categories with id " + id)); 

        this.categoryRepository.delete(category);
    }
}
