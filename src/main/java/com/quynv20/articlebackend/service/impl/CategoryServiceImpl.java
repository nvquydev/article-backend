package com.quynv20.articlebackend.service.impl;

import com.quynv20.articlebackend.dao.entity.Category;
import com.quynv20.articlebackend.dao.repository.CategoryRepository;
import com.quynv20.articlebackend.exception.ResourceNotFoundException;
import com.quynv20.articlebackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(String id) {
    	 return categoryRepository.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    @Override
    public Category createCategory(Category category) {
        // Thêm logic kiểm tra và xử lý dữ liệu nếu cần
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(String id, Category updatedCategory) {
        Category category = getCategoryById(id);

        // Cập nhật thông tin category với dữ liệu từ updatedCategory
        category.setName(updatedCategory.getName());

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(String id) {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }
}

