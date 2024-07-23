package com.moysklad.userservice.services;

import com.moysklad.userservice.DTO.CreateProductDto;
import com.moysklad.userservice.DTO.UpdateProductDto;
import com.moysklad.userservice.entities.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Integer id);
    Product createProduct(CreateProductDto userInput);
    Product updateProduct(UpdateProductDto userInput);
    void deleteProduct(Integer id);
}
