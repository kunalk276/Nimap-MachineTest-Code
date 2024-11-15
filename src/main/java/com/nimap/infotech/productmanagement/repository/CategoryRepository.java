package com.nimap.infotech.productmanagement.repository;

import com.nimap.infotech.productmanagement.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
