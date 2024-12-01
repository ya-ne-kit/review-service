package ru.test.reviews_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateProductDto {
    @NotBlank(message = "Наименование продукта не может быть пустым")
    private String name;
    @NotBlank(message = "Наименование производителя не может быть пустым")
    private String manufacturer;
}
