package com.georgedev.inventoryservice.controller;

import com.georgedev.inventoryservice.entity.Product;
import com.georgedev.inventoryservice.exception.ProductAlreadyExistException;
import com.georgedev.inventoryservice.exception.ProductNotFoundException;
import com.georgedev.inventoryservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author George Okereka
 * Created on 25/07/2020
 */

@RestController
@RequestMapping("/api/v1/inventory")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/fetch-all-products")
    public List<Product> fetchAllProducts(){
        return productService.fetchAllProducts();
    }

    @PostMapping("/add-new-product")
    public Product addNewProduct(@Valid @RequestBody Product product) throws ProductAlreadyExistException {
        return productService.createProduct(product);
    }

    @PutMapping("/update-product/{id}")
    public Product updateProduct(@PathVariable(value = "id") Long id,
                                 @Valid @RequestBody Product product) throws ProductNotFoundException, ProductAlreadyExistException {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id")Long id) throws ProductNotFoundException{
        return productService.deleteProduct(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete-all-products")
    public ResponseEntity<?> deleteAllProducts(){
        return productService.deleteAllProduct() ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
