package com.georgedev.inventoryservice.service;

import com.georgedev.inventoryservice.data.MockDataProvider;
import com.georgedev.inventoryservice.entity.Product;
import com.georgedev.inventoryservice.exception.ProductAlreadyExistException;
import com.georgedev.inventoryservice.exception.ProductNotFoundException;
import com.georgedev.inventoryservice.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author George Okereka
 * Created on 25/07/2020
 */

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product newProduct) throws ProductAlreadyExistException {

        if(productNameExist(newProduct.getName())){
            throw new ProductAlreadyExistException(newProduct.getName());
        }

        newProduct.setCreated(LocalDateTime.now());
        newProduct.setModified(LocalDateTime.now());

        return productRepository.save(newProduct);
    }

    public List<Product> fetchAllProducts(){
         try{
             List<Product> allProducts = (List<Product>) productRepository.findAll();
             return (allProducts.isEmpty() || allProducts == null)
                     ? MockDataProvider.fetchMockData()
                     : allProducts;
         } catch (Exception ex){
             LOGGER.error(String.format("An Error occured while connecting to the database. Error message %s", ex.getMessage()));
             return MockDataProvider.fetchMockData();
         }
    }

    public Product findProductById(Long id) throws ProductNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product updateProduct(Long id, Product updatedProduct) throws ProductNotFoundException, ProductAlreadyExistException {

        if(productNameExist(updatedProduct.getName())){
            throw new ProductAlreadyExistException(updatedProduct.getName());
        }

        Product product = productRepository.findProductById(id);

        if(product == null) {
            throw new ProductNotFoundException(id);
        }

        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setStock(updatedProduct.getStock());
        product.setModified(LocalDateTime.now());

        return productRepository.save(product);
    }

    public boolean  deleteProduct(Long id) throws ProductNotFoundException{
        Product product = productRepository.findProductById(id);

        if(product == null) {
            throw new ProductNotFoundException(id);
        }

        productRepository.delete(product);

        return true;
    }

    public boolean deleteAllProduct(){
        productRepository.deleteAll();

        return true;
    }

    public Boolean productNameExist(String productName){
        Product product = productRepository.findProductByName(productName);

        return (!(product == null));
    }
}
