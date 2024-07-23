package com.emarketshop.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emarketshop.product.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	
	
}
