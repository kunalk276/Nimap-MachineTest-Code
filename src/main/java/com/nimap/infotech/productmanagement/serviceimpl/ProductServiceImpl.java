package com.nimap.infotech.productmanagement.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nimap.infotech.productmanagement.dto.ProductJoin;
import com.nimap.infotech.productmanagement.entity.Product;
import com.nimap.infotech.productmanagement.exception.ResourceNotFoundException;
import com.nimap.infotech.productmanagement.repository.ProductRepository;
import com.nimap.infotech.productmanagement.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
    private static final int PAGE_SIZE = 10;
	
	@Autowired
    private ProductRepository proRepo;
	
	@Override
    public Page<Product> getAllProducts(int page) {
		
        return proRepo.findAll(PageRequest.of(page, PAGE_SIZE));
    }


	@Override
	public Product createProduct(Product product) {
		
		 return proRepo.save(product);
	}
	
	@Override
	public Product updateProduct(int id, Product product) {

		Product existing = proRepo.findById(id).orElse(null);
        if (existing != null) {
            existing.setProductName(product.getProductName());    
            return proRepo.save(existing);
        }
        return null;
	}

	@Override
	public void deleteProduct(int id) {
		
		 Product existing = proRepo.findById(id).orElse(null);
	        if (existing != null) {
	            existing.setIsDeleted(0);
	            proRepo.save(existing);
	        }
	}

	@Override
	public List<Product> getDeletedProducts() {
	
		return proRepo.findByIsDeleted(0); 
	}

	@Override
	public void restoreProduct(int id) {
		
		 Product product = proRepo.findById(id)
		        .orElseThrow(() -> new ResourceNotFoundException("product not found with id: " + id));
		 product.setIsDeleted(1); 
		 proRepo.save(product);
	}

	
	
    @Override
    public ProductJoin getProductJoinById(int id) {
        Product product = proRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        return new ProductJoin(
                product.getProductId(),
                product.getProductName(),
                product.getCategory().getCategoryId(),
                product.getCategory().getCategoryName()
        );
    }

}