package com.moysklad.userservice.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

/*@Entity*/
@Getter
@Setter
@NoArgsConstructor
public class Product {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;*/

    //@Column(nullable = false, length = 255)
    private String name;

    //@Column(length = 4096)
    private String description;

    //@Column(nullable = false)
    private BigDecimal price;

    //@Column(nullable = false)
    private Boolean inStock;

    public Product(String name, String description, BigDecimal price, boolean inStock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.inStock = inStock;
    }

}
