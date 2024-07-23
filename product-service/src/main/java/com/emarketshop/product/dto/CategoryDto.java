package com.emarketshop.product.dto;

import java.io.Serializable;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CategoryDto extends RepresentationModel<ProductDto> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer categoryId;
	private String categoryTitle;
	private String imageUrl;
	
	@JsonInclude(Include.NON_NULL)
	private Set<CategoryDto> subCategoriesDtos;
	
	@JsonProperty("parentCategory")
	@JsonInclude(Include.NON_NULL)
	private CategoryDto parentCategoryDto;
	
	@JsonInclude(Include.NON_NULL)
	private Set<ProductDto> productDtos;
	
}










