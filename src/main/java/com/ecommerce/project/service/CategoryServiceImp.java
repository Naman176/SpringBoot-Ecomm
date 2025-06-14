package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Service Annotation will create and provide the bean for this class whenever needed
@Service
public class CategoryServiceImp implements CategoryService {

//    private List<Category> categories = new ArrayList<>();
//    private Long nextId = 1L;

    // Making use of repository now
    @Autowired
    CategoryRepository categoryRepository;

//    @Override
//    public List<Category> getAllCategories() {
////        return categories;
//
//        List<Category> categories = categoryRepository.findAll();
//        if(categories.isEmpty()){
//            throw new APIException("No Categories to fetch");
//        }
//        return categories;
//    }
//
//    @Override
//    public void createCategory(Category category) {
//        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
//        if(savedCategory != null){
//            throw new APIException("Category with the name: " + category.getCategoryName() + " already exists");
//        }
//
////        category.setCategoryId(nextId++);
////        categories.add(category);
//        categoryRepository.save(category);
//    }
//
//    @Override
//    public String deleteCategory(Long categoryId) {
////        Category category = categories.stream().filter(c -> c.getCategoryId().equals(categoryId))
////                .findFirst()
////                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found"));
////
////        categories.remove(category);
////        return "Category with categoryId: " + categoryId + " Deleted Successfully";
//
////        List<Category> categories = categoryRepository.findAll();
////        Category category = categories.stream().filter(c -> c.getCategoryId().equals(categoryId))
////                .findFirst()
////                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found"));
////
////        categoryRepository.delete(category);
////        return "Category with categoryId: " + categoryId + " Deleted Successfully";
//
////        Category category = categoryRepository.findById(categoryId)
////                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found"));
//
//        Category category = categoryRepository.findById(categoryId)
//                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
//
//        categoryRepository.delete(category);
//        return "Category with categoryId: " + categoryId + " Deleted Successfully";
//    }
//
//    @Override
//    public Category updateCategory(Long categoryId, Category category) {
////        Optional<Category> optionalCategory = categories.stream().filter(c -> c.getCategoryId().equals(categoryId))
////                .findFirst();
////
////        if (optionalCategory.isPresent()) {
////            Category existingCategory = optionalCategory.get();
////            existingCategory.setCategoryName(category.getCategoryName());
////            return existingCategory;
////        } else {
////            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found");
////        }
//
////        List<Category> categories = categoryRepository.findAll();
////
////        Optional<Category> optionalCategory = categories.stream().filter(c -> c.getCategoryId().equals(categoryId))
////                .findFirst();
////
////        if (optionalCategory.isPresent()) {
////            Category existingCategory = optionalCategory.get();
////            existingCategory.setCategoryName(category.getCategoryName());
////            return categoryRepository.save(existingCategory);
////        } else {
////            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found");
////        }
//
//
    /// /        Category existingCategory = categoryRepository.findById(categoryId)
    /// /                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found"));
//
//        Category existingCategory = categoryRepository.findById(categoryId)
//                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
//
//        existingCategory.setCategoryName(category.getCategoryName());
//        return categoryRepository.save(existingCategory);
//    }


    @Autowired
    private ModelMapper modelMapper;

    // Making use of DTOs
    @Override
    public CategoryResponse getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new APIException("No Categories to fetch");
        }

        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);

        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category existingCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if (existingCategory != null) {
            throw new APIException("Category with the name: " + category.getCategoryName() + " already exists");
        }
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        categoryRepository.delete(category);
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        Category category = modelMapper.map(categoryDTO, Category.class);
        existingCategory.setCategoryName(category.getCategoryName());
        Category updatedCategory = categoryRepository.save(existingCategory);
        return modelMapper.map(updatedCategory, CategoryDTO.class);
    }

}
