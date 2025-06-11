package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Service Annotation will create and provide the bean for this class whenever needed
@Service
public class CategoryServiceImp implements CategoryService {

//    private List<Category> categories = new ArrayList<>();
//    private Long nextId = 1L;

    // Making use of repository now
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
//        return categories;
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
//        category.setCategoryId(nextId++);
//        categories.add(category);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
//        Category category = categories.stream().filter(c -> c.getCategoryId().equals(categoryId))
//                .findFirst()
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found"));
//
//        categories.remove(category);
//        return "Category with categoryId: " + categoryId + " Deleted Successfully";

//        List<Category> categories = categoryRepository.findAll();
//        Category category = categories.stream().filter(c -> c.getCategoryId().equals(categoryId))
//                .findFirst()
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found"));
//
//        categoryRepository.delete(category);
//        return "Category with categoryId: " + categoryId + " Deleted Successfully";

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found"));

        categoryRepository.delete(category);
        return "Category with categoryId: " + categoryId + " Deleted Successfully";
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) {
//        Optional<Category> optionalCategory = categories.stream().filter(c -> c.getCategoryId().equals(categoryId))
//                .findFirst();
//
//        if (optionalCategory.isPresent()) {
//            Category existingCategory = optionalCategory.get();
//            existingCategory.setCategoryName(category.getCategoryName());
//            return existingCategory;
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found");
//        }

//        List<Category> categories = categoryRepository.findAll();
//
//        Optional<Category> optionalCategory = categories.stream().filter(c -> c.getCategoryId().equals(categoryId))
//                .findFirst();
//
//        if (optionalCategory.isPresent()) {
//            Category existingCategory = optionalCategory.get();
//            existingCategory.setCategoryName(category.getCategoryName());
//            return categoryRepository.save(existingCategory);
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found");
//        }


        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found"));

        category.setCategoryId(categoryId);
        existingCategory = categoryRepository.save(category);
        return existingCategory;
    }
}
