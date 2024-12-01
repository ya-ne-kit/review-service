package ru.test.reviews_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO для обновления информации об отзыве.
 * <p>
 * Класс используется для передачи данных, необходимых для
 * обновления отзыва, включая содержание отзыва, его плюсы,
 * минусы и оценку товара.
 * <p>
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@ToString
public class UpdateReviewDto {

    /**
     * Содержание отзыва.
     * Не может быть пустым.
     */
    @NotBlank(message = "Содержание отзыва не может быть пустым")
    private String comment;

    /**
     * Плюсы товара.
     * Может быть пустым.
     */
    private String pros;

    /**
     * Минусы товара.
     * Может быть пустым.
     */
    private String cons;

    /**
     * Оценка товара.
     * Должна быть в диапазоне от 1 до 5 (включительно).
     */
    @Min(message = "Минимальное значение оценки - 1", value = 1)
    @Max(message = "Максимальное значение оценки - 5", value = 5)
    @NotNull(message = "Необходимо поставить оценку товару - от 1 до 5")
    private Integer rate;
}
