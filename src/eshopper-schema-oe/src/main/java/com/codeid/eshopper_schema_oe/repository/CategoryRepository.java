package com.codeid.eshopper_schema_oe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeid.eshopper_schema_oe.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
