package com.emarketshop.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emarketshop.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	
	
}
