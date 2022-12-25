package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CategoryDto;
import com.blog.repositories.CategoryRepo;
import com.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		Category category = this.modelMapper.map(categoryDto, Category.class);
		
		Category createdCategory = this.categoryRepository.save(category);
		
		return this.modelMapper.map(createdCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> 
		new ResourceNotFoundException("Category","Category Id",categoryId));
		
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCategory = this.categoryRepository.save(category);
		
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
    public void deleteCategory(Integer categoryId) {
		
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new
				ResourceNotFoundException("Category", "Category Id", categoryId));
		this.categoryRepository.delete(category);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = this.categoryRepository.findAll();
		
		List<CategoryDto> categoriesDto = categories.stream().map(category -> 
		                   this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		return categoriesDto;
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new 
				ResourceNotFoundException("Category", "Category Id", categoryId));
		
		return this.modelMapper.map(category, CategoryDto.class); 
	}

}
