package com.moysklad.userservice.services;

import com.moysklad.userservice.DTO.CreateProductDto;
import com.moysklad.userservice.DTO.UpdateProductDto;
import com.moysklad.userservice.entities.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private List<Product> products = new ArrayList<>();

    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        Optional<Product> optionalProduct = Optional.empty();
        if (!(id < 1) && !(id > products.size())) {
            optionalProduct = Optional.of(products.get(id - 1));
        } else {
            if (id < 1) {
                throw new IllegalArgumentException("Введен некорректный ID (не может быть меньше единицы)");
            }
            if (id > products.size()) {
                throw new IllegalStateException("Такого ID нет в списке");
            }
        }
        return optionalProduct;
    }

    @Override
    public Product createProduct(CreateProductDto userInput) {
        Product product = new Product(userInput.getProductName(),
                userInput.getProductDescription(),
                userInput.getProductPrice(),
                userInput.isProductInStock());
        products.add(product);
        return product;
    }

    @Override
    public Product updateProduct(UpdateProductDto userInput) {
        Product product = new Product();
        if (!(userInput.getId() < 1) && !(userInput.getId() > products.size())) {
            product = products.get(userInput.getId() - 1);
            if (userInput.getProductName() != null)
                product.setName(userInput.getProductName());
            if (userInput.getProductDescription() != null)
                product.setDescription(userInput.getProductDescription());
            if (userInput.getProductPrice() != null)
                product.setPrice(userInput.getProductPrice());
            if (userInput.getProductInStock() != null)
                product.setInStock(userInput.getProductInStock());
            products.set(userInput.getId() - 1, product);
        } else {
            if (userInput.getId() < 1) {
                throw new IllegalArgumentException("Введен некорректный ID (не может быть меньше единицы)");
            }
            if (userInput.getId() > products.size()) {
                throw new IllegalStateException("Такого ID нет в списке");
            }
        }
        return product;
    }

    @Override
    public void deleteProduct(Integer id) {
        if (!(id < 1) && !(id > products.size())) {
            products.remove(id - 1);
        } else {
            if (id < 1) {
                throw new IllegalArgumentException("Введен некорректный ID (не может быть меньше нуля)");
            }
            if (id > products.size()) {
                throw new IllegalStateException("Такого ID нет в списке");
            }
        }
    }
}
