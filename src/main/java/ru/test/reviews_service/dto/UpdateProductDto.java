package ru.test.reviews_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO для обновления информации о товаре.
 * <p>
 * Класс используется для передачи данных, необходимых для
 * обновления информации о продукте, включая его наименование и
 * наименование производителя.
 * <p>
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UpdateProductDto {

    /**
     * Наименование продукта.
     * Не может быть пустым.
     */
    @NotBlank(message = "Наименование продукта не может быть пустым")
    private String name;

    /**
     * Наименование производителя.
     * Не может быть пустым.
     */
    @NotBlank(message = "Наименование производителя не может быть пустым")
    private String manufacturer;
}
