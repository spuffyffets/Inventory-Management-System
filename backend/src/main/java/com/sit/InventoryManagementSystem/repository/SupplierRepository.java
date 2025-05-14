package com.sit.InventoryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sit.InventoryManagementSystem.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
