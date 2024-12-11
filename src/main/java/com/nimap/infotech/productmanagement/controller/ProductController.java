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
	
    //To fetch the products page wise , default page is 0 i.e. It will show the entries 1-10
    @GetMapping
    public Page<Product> getAllProducts(@RequestParam(defaultValue = "0") int page) {
    	
        return provice.getAllProducts(page);
    }
	

     //get the product along with the category using id
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

    //method to update the product using id
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        Product updated = provice.updateProduct(id, product);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
    
    //method to delete the product using id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        provice.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    
    //method to get all the deleted products
    @GetMapping("/deleted")
    public ResponseEntity<List<Product>> getDeletedProducts() {
        List<Product> deletedProducts = provice.getDeletedProducts();
        return ResponseEntity.ok(deletedProducts);
    }
    
    //method to restore the deleted product to main list using id
    @PutMapping("/restore/{id}")
    public ResponseEntity<Void> restoreProduct(@PathVariable int id) {
        provice.restoreProduct(id);
        return ResponseEntity.ok().build();
    }
    
    

   
	
	

	
	
}