package com.quynv20.articlebackend.controller;

import com.quynv20.articlebackend.Utils.DTOEntityConverter;
import com.quynv20.articlebackend.dao.entity.Category;
import com.quynv20.articlebackend.dto.CategoryDTO;
import com.quynv20.articlebackend.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = DTOEntityConverter.convertToEntity(categoryDTO, Category.class);
        Category createdCategory = categoryService.createCategory(category);
        CategoryDTO createdCategoryDTO = DTOEntityConverter.convertToDTO(createdCategory, CategoryDTO.class);
        return new ResponseEntity<>(createdCategoryDTO, HttpStatus.CREATED);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable String id) {
        Category category = categoryService.getCategoryById(id);
        CategoryDTO categoryDTO = DTOEntityConverter.convertToDTO(category, CategoryDTO.class);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable String id, @Valid @RequestBody CategoryDTO categoryDTO) {
        Category categoryToUpdate = DTOEntityConverter.convertToEntity(categoryDTO, Category.class);
        Category updatedCategory = categoryService.updateCategory(id, categoryToUpdate);
        CategoryDTO updatedCategoryDTO = DTOEntityConverter.convertToDTO(updatedCategory, CategoryDTO.class);
        return new ResponseEntity<>(updatedCategoryDTO, HttpStatus.OK);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
