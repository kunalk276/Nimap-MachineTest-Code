package com.nimap.infotech.productmanagement.controller;

import com.nimap.infotech.productmanagement.dto.ProductDTO;
import com.nimap.infotech.productmanagement.dto.ProductJoin;
import com.nimap.infotech.productmanagement.entity.Product;
import com.nimap.infotech.productmanagement.exception.ResourceNotFoundException;
import com.nimap.infotech.productmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService provice;
	
    
    @GetMapping
    public Page<Product> getAllProducts(@RequestParam(defaultValue = "0") int page) {
    	
        return provice.getAllProducts(page);
    }
	

    
	 @GetMapping("/{id}")
	  public ResponseEntity<ProductJoin> getProductJoinById(@PathVariable int id) {
	      ProductJoin product = provice.getProductJoinById(id);
	      return ResponseEntity.ok(product);
    }

	 //method to create the new product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return provice.createProduct(product);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        Product updated = provice.updateProduct(id, product);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        provice.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    
    
    @GetMapping("/deleted")
    public ResponseEntity<List<Product>> getDeletedProducts() {
        List<Product> deletedProducts = provice.getDeletedProducts();
        return ResponseEntity.ok(deletedProducts);
    }
    

    @PutMapping("/restore/{id}")
    public ResponseEntity<Void> restoreProduct(@PathVariable int id) {
        provice.restoreProduct(id);
        return ResponseEntity.ok().build();
    }
    
    

   
	
	

	
	
}