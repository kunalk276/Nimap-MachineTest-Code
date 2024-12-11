package com.nimap.infotech.productmanagement.controller;

import com.nimap.infotech.productmanagement.dto.CategoryDTO;
import com.nimap.infotech.productmanagement.entity.Category;
import com.nimap.infotech.productmanagement.exception.ResourceNotFoundException;
import com.nimap.infotech.productmanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService catvice;
	
	//To fetch the categories page wise , default page is 0 i.e. It will show the entries 1-10
	@GetMapping
    public Page<Category> getAllCategories(@RequestParam(defaultValue = "0") int page) {
		
        return catvice.getAllCategories(page);
    }
	

    //Method to call specific category using id
    @GetMapping("/{id}")         
    public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
        Category category = catvice .getCategoryById(id);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
    }
    
    //method to create the new category
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return catvice.createCategory(category);
    }

    //method to update the category using id
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category category) {
        Category updated = catvice.updateCategory(id, category);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
    
    //method to delete the category using id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        catvice.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
    
    //method to see the list of deleted category
    @GetMapping("/deleted")
    public ResponseEntity<List<Category>> getDeletedCategories() {
        List<Category> deletedCategories = catvice.getDeletedCategories();
        return ResponseEntity.ok(deletedCategories);
    }

    
    // method to restore the deleted category to the main list using id
    @PutMapping("/restore/{id}")
    public ResponseEntity<Void> restoreCategory(@PathVariable int id) {
        catvice.restoreCategory(id);
        return ResponseEntity.ok().build();
    }
    
 
 
	
	

}