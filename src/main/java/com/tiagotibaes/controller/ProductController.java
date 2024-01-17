package com.tiagotibaes.controller;

import com.tiagotibaes.domain.entity.Client;
import com.tiagotibaes.domain.entity.Product;
import com.tiagotibaes.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/{id}")
    public Product searchProductById(@PathVariable Integer id) throws ResponseStatusException {
        Optional<Product> Productd = productService.getProductById(id);

        return Productd
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado")
                );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct(@RequestBody Product product) {
        return productService.createClient(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Integer id){
        boolean ProductWasDeleted = productService.deleteProductById(id);
        if (!ProductWasDeleted){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não existe!");
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCliente(@PathVariable Integer id, @RequestBody Product product){
        Product update = productService.updateProductById(id, product);
        if(update == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não existe!");
        }
    }


    @GetMapping("/filter")
    public List<Product> searchProductByFilter(Product product) {
        return productService.findListProductsByFilter(product);
    }
}
