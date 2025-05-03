package com.codeid.eshopper_schema_oe.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codeid.eshopper_schema_oe.entities.Category;
import com.codeid.eshopper_schema_oe.repository.CategoryRepository;
import com.codeid.eshopper_schema_oe.service.CategoryService;

@Service
public  class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    //constructor injection
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }
}
