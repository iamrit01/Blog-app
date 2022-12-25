package com.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.CategoryDto;
import com.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	// post method
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto addedCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(addedCategory,HttpStatus.CREATED);
	}
	
	
	// put method
	@PutMapping("{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
		CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, categoryId);
		return ResponseEntity.ok(updatedCategory);
	}
	
	
	// delete method
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category" + categoryId +" deleted successfully",true),HttpStatus.OK);
	}
	
	
	// get method
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> categories = this.categoryService.getAllCategory();
		return ResponseEntity.ok(categories);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId){
		CategoryDto categoryDto = this.categoryService.getCategory(categoryId);
		return ResponseEntity.ok(categoryDto);
	}

}
