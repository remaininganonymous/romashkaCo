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
        if (!(id < 0) && !(id > products.size() - 1)) {
            optionalProduct = Optional.of(products.get(id));
        } else {
            if (id < 0) {
                throw new IllegalArgumentException("Введен некорректный ID (не может быть меньше нуля)");
            }
            if (id > products.size() - 1) {
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
        products.get(products.size() - 1).setId(products.size()); //не работает
        return product;
    }

    @Override
    public Product updateProduct(UpdateProductDto userInput) { //
        Product product = new Product();
        if (!(userInput.getId() < 0) && !(userInput.getId() > products.size() - 1)) {
            product = products.get(userInput.getId() - 1);
            if (userInput.getProductName() != null) product.setName(userInput.getProductName());
            if (userInput.getProductDescription() != null) product.setDescription(userInput.getProductDescription());
            if (userInput.getProductPrice() != null) product.setPrice(userInput.getProductPrice());
            if (userInput.getProductInStock() != null) product.setInStock(userInput.getProductInStock());
            products.set(userInput.getId(), product);
        } else {
            if (userInput.getId() < 0) {
                throw new IllegalArgumentException("Введен некорректный ID (не может быть меньше нуля)");
            }
            if (userInput.getId() > products.size() - 1) {
                throw new IllegalStateException("Такого ID нет в списке");
            }
        }
        return product;
    }

    @Override
    public void deleteProduct(Integer id) {
        if (!(id < 0) && !(id > products.size() - 1)) {
            products.remove(id);
        } else {
            if (id < 0) {
                throw new IllegalArgumentException("Введен некорректный ID (не может быть меньше нуля)");
            }
            if (id > products.size() - 1) {
                throw new IllegalStateException("Такого ID нет в списке");
            }
        }
    }
}
