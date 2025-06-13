package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;

import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

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
    @GetMapping("/public/category/all")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> allCategories = categoryService.getAllCategories();
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }

    @PostMapping("/public/category/new")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category Added Successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/category/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
//        try {
//            String status = categoryService.deleteCategory(categoryId);
//            return new ResponseEntity<>(status, HttpStatus.OK);
////            return ResponseEntity.ok(status);
////            return ResponseEntity.status(HttpStatus.OK).body(status);
//        } catch (ResponseStatusException e) {
//            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
//        }


//        We dont need try catch now as we are handling exceptions by making our own custom exceptions and handling them
//        in the service imp
        String status = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PutMapping("/public/category/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable Long categoryId, @Valid @RequestBody Category category) {
//        try {
//            Category updatedCategory = categoryService.updateCategory(categoryId, category);
//            return new ResponseEntity<>("Category with category id: " + categoryId + " Updated", HttpStatus.OK);
//        } catch (ResponseStatusException e) {
//            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
//        }


        Category updatedCategory = categoryService.updateCategory(categoryId, category);
        return new ResponseEntity<>("Category with category id: " + categoryId + " Updated", HttpStatus.OK);

    }
}
