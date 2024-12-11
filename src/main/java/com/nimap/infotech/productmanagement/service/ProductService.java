package com.nimap.infotech.productmanagement.service;


import com.nimap.infotech.productmanagement.dto.ProductJoin;
import com.nimap.infotech.productmanagement.entity.Product;

import java.util.List;

import org.springframework.data.domain.Page;

public interface ProductService {
	
    Page<Product> getAllProducts(int page);
	 
	 ProductJoin getProductJoinById(int id);
	 
    Product createProduct(Product product);
   
	 Product updateProduct(int id, Product product);
	 
	 void deleteProduct(int id);
	 
	 List<Product> getDeletedProducts();
	 
	 void restoreProduct(int id);


}