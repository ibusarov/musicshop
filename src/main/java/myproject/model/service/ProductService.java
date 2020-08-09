package myproject.model.service;

import myproject.model.entity.Product;
import myproject.service.ProductServiceModel;

import java.util.Collection;
import java.util.List;

public interface ProductService {

    List<String> findAllProdNames();

    ProductServiceModel findByName(String name);

    void addProduct(ProductServiceModel productServiceModel);

    Collection<Product> productList();

    ProductServiceModel findById(String id);

    void delete(String id);
}
