package com.sit.InventoryManagementSystem.exceptions;

public class NameValueRequiredException extends RuntimeException{
    public NameValueRequiredException(String message){
        super(message);
    }
}
