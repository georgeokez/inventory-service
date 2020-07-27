package com.georgedev.inventoryservice.data;

import com.georgedev.inventoryservice.entity.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author George Okereka
 * Created on 27/07/2020
 */

public final class MockDataProvider {

    public static List<Product> fetchMockData(){
        
        List<Product> mockProducts = new ArrayList<>();
        String [] productNames = {"Football", "Basket Ball", "Baseball"};

        for(int i = 0; i < productNames.length; i++){
            mockProducts.add(getNewProduct((long) (i + 1), productNames[i]));
        }

        return mockProducts;
    }
    
    
    private static Product getNewProduct(Long id, String productName){
        Product mockProduct = new Product();
        mockProduct.setId(id);
        mockProduct.setName(productName);
        mockProduct.setDescription(productName);
        mockProduct.setStock(10);
        mockProduct.setCreated(LocalDateTime.now());
        mockProduct.setModified(LocalDateTime.now());

        return mockProduct;
    }

}
