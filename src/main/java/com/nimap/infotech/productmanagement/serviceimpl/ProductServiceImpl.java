package com.nimap.infotech.productmanagement.serviceimpl;


import com.nimap.infotech.productmanagement.dto.ProductDTO;
import com.nimap.infotech.productmanagement.entity.Product;
import com.nimap.infotech.productmanagement.repository.ProductRepository;
import com.nimap.infotech.productmanagement.service.ProductService;

import jakarta.transaction.Transactional;

import com.nimap.infotech.productmanagement.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductDTO> getAllProducts(int page, int size) {
        var pageable = PageRequest.of(page, size);
        var products = productRepository.findAll(pageable).getContent();
        return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return convertToDTO(product);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found")));
        product = productRepository.save(product);
        return convertToDTO(product);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found")));
        product = productRepository.save(product);
        return convertToDTO(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setProductId(product.getProductId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setCategoryId(product.getCategory().getCategoryId());
        return dto;
    }
}
