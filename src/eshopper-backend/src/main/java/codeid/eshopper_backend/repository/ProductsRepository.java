package codeid.eshopper_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import codeid.eshopper_backend.model.entity.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long>{

}
