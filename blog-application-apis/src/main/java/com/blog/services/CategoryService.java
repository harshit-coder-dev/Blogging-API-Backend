package com.blog.services;

import com.blog.DTO.CategoryDto;

import java.util.List;

public interface CategoryService {

    public CategoryDto createCategory(CategoryDto categoryDto);

    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    public void deleteCategory(Integer categoryId);

    public CategoryDto getCategoryById(Integer categoryId);

    public List<CategoryDto> getAllCategory();
}
