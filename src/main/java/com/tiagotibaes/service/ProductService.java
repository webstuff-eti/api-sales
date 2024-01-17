package com.tiagotibaes.service;

import com.tiagotibaes.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public Product createClient(Product product);
    public Optional<Product> getProductById(Integer id);

    public boolean deleteProductById(Integer id);

    public Product updateProductById(Integer id, Product product);

    public List<Product> findListProductsByFilter(Product product);
}
