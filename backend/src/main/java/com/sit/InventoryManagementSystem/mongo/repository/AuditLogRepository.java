package com.sit.InventoryManagementSystem.mongo.repository;

import com.sit.InventoryManagementSystem.mongo.model.AuditLog;

import java.util.Optional;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepository extends MongoRepository<AuditLog, String> {
	Optional<AuditLog> findByUsername(String username);

}
