package ru.test.reviews_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO для представления нового отзыва.
 * <p>
 * Класс используется для передачи данных о новом отзыве,
 * включая содержание отзыва, положительные и отрицательные стороны,
 * оценку и адрес электронной почты автора отзыва.
 * <p>
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@ToString
public class NewReviewDto {

    /**
     * Содержание отзыва.
     * Не может быть пустым.
     */
    @NotBlank(message = "Содержание отзыва не может быть пустым")
    private String comment;

    /**
     * Положительные стороны продукта, указанные в отзыве.
     * Может быть пустым.
     */
    private String pros;

    /**
     * Отрицательные стороны продукта, указанные в отзыве.
     * Может быть пустым.
     */
    private String cons;

    /**
     * Оценка товара.
     * Должна быть в диапазоне от 1 до 5, включительно.
     */
    @Min(value = 1, message = "Минимальное значение оценки - 1")
    @Max(value = 5, message = "Максимальное значение оценки - 5")
    @NotNull(message = "Необходимо поставить оценку товару - от 1 до 5")
    private Integer rate;

    /**
     * Адрес электронной почты автора отзыва.
     */
    @Email(message = "Некорректный формат email")
    @NotNull(message = "Email автора отзыва не может быть пустым")
    private String email;
}
