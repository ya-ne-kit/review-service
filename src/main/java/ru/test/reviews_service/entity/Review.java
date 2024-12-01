package ru.test.reviews_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Сущность, представляющая отзыв о товаре.
 * <p>
 * Класс используется для хранения информации об отзыве,
 * включая его идентификатор, дату, комментарий, положительные и отрицательные стороны,
 * оценку и адрес электронной почты пользователя, оставившего отзыв.
 * <p>
 */
@Entity
@Getter
@Setter
public class Review {

    /**
     * Уникальный идентификатор отзыва.
     * Генерируется автоматически при создании.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Дата, когда был оставлен отзыв.
     * Значение даты устанавливается на стороне сервиса.
     * Хранится в формате LocalDate.
     */
    private LocalDate date;

    /**
     * Комментарий пользователя к товару.
     * Содержит текст отзыва.
     */
    private String comment;

    /**
     * Положительные стороны продукта, указанные пользователем.
     */
    private String pros;

    /**
     * Отрицательные стороны продукта, указанные пользователем.
     */
    private String cons;

    /**
     * Оценка продукта, выставленная пользователем.
     * Ожидается значение в диапазоне от 1 до 5.
     */
    private Integer rate;

    /**
     * Адрес электронной почты пользователя, оставившего отзыв.
     */
    private String email;

    /**
     * Продукт, к которому относится данный отзыв.
     * Связь "многие к одному" с сущностью Product.
     */
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
