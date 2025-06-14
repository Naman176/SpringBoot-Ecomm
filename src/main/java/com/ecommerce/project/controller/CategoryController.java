package com.ecommerce.project.controller;

import com.ecommerce.project.config.AppConstants;
import com.ecommerce.project.model.Category;

import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Below is Constructor Injection, but we can use Autowired annotation for field injection and
    // get rid of above.
//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }

    //    @RequestMapping(value = "/api/public/category/all", method = RequestMethod.GET)
//    @GetMapping("/public/category/all")
//    public ResponseEntity<List<Category>> getAllCategories() {
//        List<Category> allCategories = categoryService.getAllCategories();
//        return new ResponseEntity<>(allCategories, HttpStatus.OK);
//    }
//
//    @PostMapping("/public/category/new")
//    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category) {
//        categoryService.createCategory(category);
//        return new ResponseEntity<>("Category Added Successfully", HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/admin/category/{categoryId}")
//    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
////        try {
////            String status = categoryService.deleteCategory(categoryId);
////            return new ResponseEntity<>(status, HttpStatus.OK);
//////            return ResponseEntity.ok(status);
//////            return ResponseEntity.status(HttpStatus.OK).body(status);
////        } catch (ResponseStatusException e) {
////            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
////        }
//
//
////        We dont need try catch now as we are handling exceptions by making our own custom exceptions and handling them
////        in the service imp
//        String status = categoryService.deleteCategory(categoryId);
//        return new ResponseEntity<>(status, HttpStatus.OK);
//    }
//
//    @PutMapping("/public/category/{categoryId}")
//    public ResponseEntity<String> updateCategory(@PathVariable Long categoryId, @Valid @RequestBody Category category) {

    /// /        try {
    /// /            Category updatedCategory = categoryService.updateCategory(categoryId, category);
    /// /            return new ResponseEntity<>("Category with category id: " + categoryId + " Updated", HttpStatus.OK);
    /// /        } catch (ResponseStatusException e) {
    /// /            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
    /// /        }
//
//
//        Category updatedCategory = categoryService.updateCategory(categoryId, category);
//        return new ResponseEntity<>("Category with category id: " + categoryId + " Updated", HttpStatus.OK);
//
//    }

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/public/category/all")
    public ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {
        CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PostMapping("/public/category/new")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody Category category) {
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/category/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId) {
        CategoryDTO deletedCategoryDTO = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(deletedCategoryDTO, HttpStatus.OK);
    }

    @PutMapping("/public/category/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long categoryId, @Valid @RequestBody Category category) {
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        CategoryDTO updatedCategoryDTO = categoryService.updateCategory(categoryId, categoryDTO);
        return new ResponseEntity<>(updatedCategoryDTO, HttpStatus.OK);
    }
}
