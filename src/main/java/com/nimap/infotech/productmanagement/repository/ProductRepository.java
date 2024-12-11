package com.nimap.infotech.productmanagement.repository;

import com.nimap.infotech.productmanagement.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	List<Product> findByIsDeleted(int isDeleted);

}