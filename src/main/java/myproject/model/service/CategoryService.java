package myproject.model.service;

import myproject.model.entity.Category;
import myproject.model.entity.CategoryName;
import myproject.service.CategoryServiceModel;

public interface CategoryService {

    CategoryServiceModel findByCategoryName(CategoryName categoryName);

    Category find(CategoryName categoryName);
}
