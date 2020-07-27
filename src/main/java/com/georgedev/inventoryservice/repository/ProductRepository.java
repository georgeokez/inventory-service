package com.georgedev.inventoryservice.repository;

import com.georgedev.inventoryservice.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author George Okereka
 * Created on 25/07/2020
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findProductById(Long id);

    Product findProductByName(String name);

}
