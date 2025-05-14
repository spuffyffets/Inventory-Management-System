package com.sit.InventoryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sit.InventoryManagementSystem.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
