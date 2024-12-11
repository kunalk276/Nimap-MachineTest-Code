package com.nimap.infotech.productmanagement.repository;

import com.nimap.infotech.productmanagement.entity.Category;

import java.util.List;




import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	//method for deleted category
	List<Category> findByIsDeleted(int isDeleted);
}