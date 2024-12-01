package ru.test.reviews_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewProductDto {
    @NotBlank(message = "Наименование продукта не может быть пустым")
    private String name;
    @NotBlank(message = "Категория не может быть пустой")
    private String category;
    @NotBlank(message = "Наименование производителя не может быть пустым")
    private String manufacturer;
}
