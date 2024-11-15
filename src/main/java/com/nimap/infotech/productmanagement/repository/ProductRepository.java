package com.nimap.infotech.productmanagement.repository;

import com.nimap.infotech.productmanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
