package com.nimap.infotech.productmanagement.serviceimpl;


import com.nimap.infotech.productmanagement.dto.CategoryDTO;
import com.nimap.infotech.productmanagement.entity.Category;
import com.nimap.infotech.productmanagement.exception.ResourceNotFoundException;
import com.nimap.infotech.productmanagement.repository.CategoryRepository;
import com.nimap.infotech.productmanagement.service.CategoryService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private static final int PAGE_SIZE = 10;
	
	@Autowired
    private CategoryRepository catRepo;
	
	
    @Override
    public Page<Category> getAllCategories(int page) {
    	
	      return catRepo.findAll(PageRequest.of(page, PAGE_SIZE));
	}
    
	@Override
	public Category getCategoryById(int id) {
		
		return catRepo.findById(id).orElse(null);
	}

	@Override
	public Category createCategory(Category category) {
		
		 return catRepo.save(category);
	}

	@Override
	public Category updateCategory(int id, Category category) {

		Category existing = catRepo.findById(id).orElse(null);
        if (existing != null) {
            existing.setCategoryName(category.getCategoryName());    
            return catRepo.save(existing);
        }
        return null;
	}

	@Override
	public void deleteCategory(int id) {
		
		 Category existing = catRepo.findById(id).orElse(null);
	        if (existing != null) {
	            existing.setIsDeleted(0);
	            catRepo.save(existing);
	        }
	}

	@Override
	public List<Category> getDeletedCategories() {
	
		return catRepo.findByIsDeleted(0); 
	}

	@Override
	public void restoreCategory(int id) {
		
		 Category category = catRepo.findById(id)
		        .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
		 category.setIsDeleted(1); 
		 catRepo.save(category);
	}


}