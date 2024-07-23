package com.emarketshop.product.resource;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Locale;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emarketshop.product.dto.CategoryDto;
import com.emarketshop.product.dto.response.collection.DtoCollectionResponse;
import com.emarketshop.product.service.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/categories")
@Slf4j
@RequiredArgsConstructor
public class CategoryResource {
	
	private final CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<DtoCollectionResponse<CategoryDto>> findAll() {
		log.info("*** CategoryDto List, controller; fetch all categories *");
		return ResponseEntity.ok(new DtoCollectionResponse<>(this.categoryService.findAll()));
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> findById(
			@PathVariable("categoryId") 
			@NotBlank(message = "Input must not be blank") 
			@Valid final String categoryId,
			@RequestHeader(value = "Accept-Language",required = false) Locale locale) {
		log.info("*** CategoryDto, resource; fetch category by id *");

		CategoryDto categoryDto = this.categoryService.findById(Integer.parseInt(categoryId), locale);

		categoryDto.add( 
			linkTo(methodOn(CategoryResource.class).findById(categoryId, locale)).withSelfRel(),
			linkTo(methodOn(CategoryResource.class).save(categoryDto)).withRel("save"),
			linkTo(methodOn(CategoryResource.class).update(categoryDto)).withRel("update"),
			linkTo(methodOn(CategoryResource.class).deleteById(String.format("%d", categoryDto.getCategoryId()))).withRel("deleteById")
		);

		return ResponseEntity.ok(categoryDto);
	}
	
	@PostMapping
	public ResponseEntity<CategoryDto> save(
			@RequestBody 
			@NotNull(message = "Input must not be NULL") 
			@Valid final CategoryDto categoryDto) {
		log.info("*** CategoryDto, resource; save category *");
		return ResponseEntity.ok(this.categoryService.save(categoryDto));
	}
	
	@PutMapping
	public ResponseEntity<CategoryDto> update(
			@RequestBody 
			@NotNull(message = "Input must not be NULL") 
			@Valid final CategoryDto categoryDto) {
		log.info("*** CategoryDto, resource; update category *");
		return ResponseEntity.ok(this.categoryService.update(categoryDto));
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> update(
			@PathVariable("categoryId")
			@NotBlank(message = "Input must not be blank")
			@Valid final String categoryId,
			@RequestBody 
			@NotNull(message = "Input must not be NULL") 
			@Valid final CategoryDto categoryDto,
			@RequestHeader(value = "Accept-Language",required = false) Locale locale) {
		log.info("*** CategoryDto, resource; update category with categoryId *");
		return ResponseEntity.ok(this.categoryService.update(Integer.parseInt(categoryId), categoryDto, locale));
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<Boolean> deleteById(@PathVariable("categoryId") final String categoryId) {
		log.info("*** Boolean, resource; delete category by id *");
		this.categoryService.deleteById(Integer.parseInt(categoryId));
		return ResponseEntity.ok(true);
	}
	
	
	
}










