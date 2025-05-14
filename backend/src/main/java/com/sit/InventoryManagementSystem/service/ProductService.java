package com.sit.InventoryManagementSystem.service;

import com.sit.InventoryManagementSystem.dto.ProductDTO;
import com.sit.InventoryManagementSystem.dto.Response;

public interface ProductService {
    Response saveProduct(ProductDTO productDTO);  // Removed imageFile
    Response updateProduct(ProductDTO productDTO);  // Removed imageFile
    Response getAllProducts();
    Response getProductById(Long id);
    Response deleteProduct(Long id);
}
