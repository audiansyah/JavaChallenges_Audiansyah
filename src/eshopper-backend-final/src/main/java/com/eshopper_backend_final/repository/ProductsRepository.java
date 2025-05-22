package com.eshopper_backend_final.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.eshopper_backend_final.model.entity.Products;


@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

    @Query("SELECT e FROM Products e WHERE LOWER(e.productName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Products> findProductsByProductNameLike(@Param("product") String keyword);


    @Query("SELECT e FROM Products e WHERE LOWER(e.category.categoryName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Products> findProductsByCategoryNameLike(@Param("category") String keyword);

    Page<Products> findByProductNameContainingIgnoreCase(String keyword, Pageable pageable);

    Page<Products> findByCategoryCategoryNameContainingIgnoreCase(String keyword, Pageable pageable);
}
