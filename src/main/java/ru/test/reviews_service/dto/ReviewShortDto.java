package ru.test.reviews_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * DTO для представления сокращенной версии отзыва.
 * <p>
 * Класс используется для передачи данных о существующем отзыве,
 * включая его идентификатор, дату, содержание отзыва, положительные и
 * отрицательные стороны, оценку и адрес электронной почты автора отзыва.
 * <p>
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ReviewShortDto {

    /**
     * Уникальный идентификатор отзыва.
     */
    private Long id;

    /**
     * Дата создания отзыва.
     */
    private LocalDate date;

    /**
     * Содержание отзыва.
     */
    private String comment;

    /**
     * Положительные стороны товара, указанные в отзыве.
     */
    private String pros;

    /**
     * Отрицательные стороны товара, указанные в отзыве.
     */
    private String cons;

    /**
     * Оценка товара.
     */
    private Integer rate;

    /**
     * Адрес электронной почты автора отзыва.
     */
    private String email;
}
