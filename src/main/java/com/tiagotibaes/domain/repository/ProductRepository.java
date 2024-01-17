package com.tiagotibaes.domain.repository;

import com.tiagotibaes.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
