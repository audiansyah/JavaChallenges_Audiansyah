package com.eshopper_backend_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eshopper_backend_final.model.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
