package com.georgedev.inventoryservice.exception;

/**
 * @author George Okereka
 * Created on 25/07/2020
 */

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(Long id) {
        super(String.format("No Product with id (%s) found.", id));
    }
}
