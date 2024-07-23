package com.emarketshop.product.service;

import java.util.List;
import java.util.Locale;

import com.emarketshop.product.dto.CategoryDto;

public interface CategoryService {
	
	List<CategoryDto> findAll();
	// CategoryDto findById(final Integer categoryId);
	CategoryDto findById(final Integer categoryId, final Locale locale);
	CategoryDto save(final CategoryDto categoryDto);
	CategoryDto update(final CategoryDto categoryDto);
	// CategoryDto update(final Integer categoryId, final CategoryDto categoryDto);
	CategoryDto update(final Integer categoryId, final CategoryDto categoryDto, final Locale locale);
	void deleteById(final Integer categoryId);
	
}
