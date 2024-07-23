package com.moysklad.userservice.controllers;

import com.moysklad.userservice.DTO.CreateProductDto;
import com.moysklad.userservice.DTO.UpdateProductDto;
import com.moysklad.userservice.entities.Product;
import com.moysklad.userservice.exceptions.InvalidIDException;
import com.moysklad.userservice.services.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/")
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Integer id) {
        try {
            Product product = productService.getProductById(id)
                    .orElseThrow(() -> new InvalidIDException()); // TODO: сделать свое исключение
            return ResponseEntity.ok(product); // дополнить
        } catch (RuntimeException e) { // TODO
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Введен неверный ID продукта: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody @Valid CreateProductDto userInput) {
        try {
            Product product = productService.createProduct(userInput);
            URI location = ServletUriComponentsBuilder
                    .fromPath("/" + productService.getAllProducts().indexOf(product))
                    .build()
                    .toUri();
            return ResponseEntity.created(location).body(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка при добавлении продукта: " + e.getMessage());
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody @Valid UpdateProductDto userInput) {
        try {
            Product updatedProduct = productService.updateProduct(userInput);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка при изменении продукта: " + e.getMessage());
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok("Продукт успешно удален");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Ошибка при удалении продукта: " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Ошибка при удалении продукта: " + e.getMessage());
        }
    }
}
