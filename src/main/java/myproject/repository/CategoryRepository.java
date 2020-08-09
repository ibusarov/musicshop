package myproject.repository;

import myproject.model.entity.Category;
import myproject.model.entity.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {

    Optional<Category>findByCategoryName(CategoryName categoryName);


}
