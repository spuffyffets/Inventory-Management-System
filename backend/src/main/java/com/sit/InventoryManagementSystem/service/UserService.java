package com.sit.InventoryManagementSystem.service;

import com.sit.InventoryManagementSystem.dto.LoginRequest;
import com.sit.InventoryManagementSystem.dto.RegisterRequest;
import com.sit.InventoryManagementSystem.dto.Response;
import com.sit.InventoryManagementSystem.dto.UserDTO;
import com.sit.InventoryManagementSystem.entity.User;

public interface UserService {
    Response registerUser(RegisterRequest registerRequest);
    Response loginUser(LoginRequest loginRequest);
    Response getAllUsers();
    User getCurrentLoggedInUser();
    Response updateUser(Long id, UserDTO userDTO);
    Response deleteUser(Long id);
    Response getUserTransactions(Long id);
}
