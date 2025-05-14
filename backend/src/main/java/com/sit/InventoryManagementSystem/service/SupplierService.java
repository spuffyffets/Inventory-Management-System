package com.sit.InventoryManagementSystem.service;

import com.sit.InventoryManagementSystem.dto.Response;
import com.sit.InventoryManagementSystem.dto.SupplierDTO;

public interface SupplierService {
    Response addSupplier(SupplierDTO supplierDTO);
    Response updateSupplier(Long id, SupplierDTO supplierDTO);
    Response getAllSuppliers();
    Response getSupplierById(Long id);
    Response deleteSupplier(Long id);
}
