package com.sit.InventoryManagementSystem.mongo.service;

import com.sit.InventoryManagementSystem.mongo.model.AuditLog;
import com.sit.InventoryManagementSystem.mongo.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    public void logAction(String username, String adminName, String action, String details) {
        AuditLog log = AuditLog.builder()
                .username(username)
                .adminName(adminName)  
                .action(action)
                .details(details)
                .timestamp(LocalDateTime.now())
                .build();

        auditLogRepository.save(log);
    }

}
