package com.sit.InventoryManagementSystem.mongo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "audit_logs")
@Data 
@AllArgsConstructor 
@NoArgsConstructor  
@Builder            
public class AuditLog {

    @Id
    private String id;

    private String username;
    private String action;
    private String details;
    private LocalDateTime timestamp;
    private String adminName;
}
