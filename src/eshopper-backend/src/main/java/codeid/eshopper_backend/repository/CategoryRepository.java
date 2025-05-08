package codeid.eshopper_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import codeid.eshopper_backend.model.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
