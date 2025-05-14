package com.sit.InventoryManagementSystem.service;

import com.sit.InventoryManagementSystem.dto.CategoryDTO;
import com.sit.InventoryManagementSystem.dto.Response;

public interface CategoryService {
    Response createCategory(CategoryDTO categoryDTO);
    Response getAllCategories();
    Response getCategoryById(Long id);
    Response updateCategory(Long id, CategoryDTO categoryDTO);
    Response deleteCategory(Long id);
}
