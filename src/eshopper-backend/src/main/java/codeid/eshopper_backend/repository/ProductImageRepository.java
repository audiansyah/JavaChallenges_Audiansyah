package codeid.eshopper_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import codeid.eshopper_backend.model.entity.ProductImage;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage,Long>{
    //jpql, basedn on object
    @Query(value="SELECT d from ProductImage d where d.products.productId=:id")
    List<ProductImage> findProductImageByProductIdJpql(@Param("id") Long id);

    //nativesql
    @Query(value="SELECT * FROM oe.product_images d where d.product_id=:id",nativeQuery = true)
    List<ProductImage> findProductImageByProductIdSql(@Param("id") Long id);
}