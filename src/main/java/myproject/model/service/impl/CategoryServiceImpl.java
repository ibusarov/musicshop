package myproject.model.service.impl;
import myproject.model.entity.Category;
import myproject.model.entity.CategoryName;
import myproject.model.service.CategoryService;
import myproject.repository.CategoryRepository;
import myproject.service.CategoryServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryServiceModel findByCategoryName(CategoryName categoryName) {
        return this.categoryRepository.findByCategoryName(categoryName)
                    .map(category -> this.modelMapper
                    .map(category,CategoryServiceModel.class)).orElse(null);

    }

    @Override
    public Category find(CategoryName categoryName) {

        return this.categoryRepository.findByCategoryName(categoryName).orElse(null) ;
    }
}
