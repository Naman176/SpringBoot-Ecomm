package com.ecommerce.project.service;

import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;

//public interface CategoryService {
//    List<Category> getAllCategories();
//
//    void createCategory(Category category);
//
//    String deleteCategory(Long categoryId);
//
//    Category updateCategory(Long categoryId, Category category);
//}


// Making use of DTOs
public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO);
}