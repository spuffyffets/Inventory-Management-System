package com.sit.InventoryManagementSystem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.sit.InventoryManagementSystem.dto.Response;
import com.sit.InventoryManagementSystem.dto.SupplierDTO;
import com.sit.InventoryManagementSystem.service.SupplierService;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;


    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> addSupplier(@RequestBody @Valid SupplierDTO supplierDTO) {
        return ResponseEntity.ok(supplierService.addSupplier(supplierDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response> getSupplierById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateSupplier(@PathVariable Long id, @RequestBody @Valid SupplierDTO supplierDTO) {
        return ResponseEntity.ok(supplierService.updateSupplier(id, supplierDTO));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteSupplier(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.deleteSupplier(id));
    }


}
