package com.moysklad.userservice.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateProductDto {
    @NotNull(message = "ID обязателен при обновлении продукта")
    private Integer id;

    @Size(max = 255, message = "Название товара не может превышать 255 символов")
    private String productName;

    @Size(max = 4096, message = "Описание товара не может превышать 4096 символов")
    private String productDescription;

    @Min(value = 0, message = "Цена товара не может быть меньше 0")
    private BigDecimal productPrice;

    private Boolean productInStock;
}
