package com.qa.repository;

import com.qa.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import com.qa.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
}
