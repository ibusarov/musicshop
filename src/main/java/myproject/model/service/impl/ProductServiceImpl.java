package myproject.model.service.impl;

import myproject.model.entity.Product;
import myproject.model.service.ProductService;
import myproject.repository.ProductRepository;
import myproject.service.ProductServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<String> findAllProdNames() {
        return this.productRepository
                .findAll()
                .stream()
                .map(Product::getName)
                .collect(Collectors.toList());
    }

    @Override
    public ProductServiceModel findByName(String name) {
        return this.productRepository.findByName(name)
                .map(prod -> this.modelMapper.map(prod,ProductServiceModel.class))
                .orElse(null);

    }

    @Override
    public void addProduct(ProductServiceModel productServiceModel) {

        this.productRepository.saveAndFlush(this.modelMapper.map(productServiceModel,Product.class));

    }

    @Override
    public Collection<Product> productList() {
        return this.productRepository.findAll(Sort.by("name"));
    }

    @Override
    public ProductServiceModel findById(String id) {
        return this.productRepository
                .findById(id)
                .map(product -> this.modelMapper.map(product,ProductServiceModel.class))
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("Product with ID=%s not found.", id)));
    }

    @Override
    public void delete(String id) {
        this.productRepository.deleteById(id);
    }


}
