package ru.test.reviews_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO для представления нового продукта.
 * <p>
 * Класс используется для передачи данных при создании товара,
 * включая его наименование, категорию и производителя.
 * <p>
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class NewProductDto {

    /**
     * Наименование продукта.
     * Не может быть пустым.
     */
    @NotBlank(message = "Наименование продукта не может быть пустым")
    private String name;

    /**
     * Категория продукта.
     * Не может быть пустой.
     */
    @NotBlank(message = "Категория не может быть пустой")
    private String category;

    /**
     * Наименование производителя продукта.
     * Не может быть пустым.
     */
    @NotBlank(message = "Наименование производителя не может быть пустым")
    private String manufacturer;
}
