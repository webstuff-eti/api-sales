package com.tiagotibaes.service.impl;

import com.tiagotibaes.domain.entity.Client;
import com.tiagotibaes.domain.entity.Product;
import com.tiagotibaes.domain.repository.ProductRepository;
import com.tiagotibaes.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product createClient(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public boolean deleteProductById(Integer id) {
        Optional<Product> existProduct =  productRepository.findById(id);
        if(existProduct.isPresent()){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Product updateProductById(Integer id, Product product) {
        Optional<Product> existProduct =  productRepository.findById(id);
        Product modifiedProduct = new Product();
        if(existProduct.isPresent()){
            product.setId(id);
            modifiedProduct =  productRepository.save(product);
        }
        return modifiedProduct;
    }

    @Override
    public List<Product> findListProductsByFilter(Product filterProduct) {
        ExampleMatcher matcher =
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(
                                ExampleMatcher.StringMatcher.CONTAINING);

        Example exampleFilterProduct = Example.of(filterProduct, matcher);
        List<Product> products = productRepository.findAll(exampleFilterProduct);
        return products;
    }
}
