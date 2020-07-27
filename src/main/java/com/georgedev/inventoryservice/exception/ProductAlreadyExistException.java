package com.georgedev.inventoryservice.exception;

public class ProductAlreadyExistException extends Exception {

    public ProductAlreadyExistException(String productName) {
        super(String.format("This product already exist (%s)", productName));
    }
}
