package ru.test.reviews_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.test.reviews_service.util.Category;

/**
 * Сущность, представляющая товар.
 * <p>
 * Класс используется для хранения информации о товаре,
 * включая его идентификатор, название, производителя и категорию.
 * <p>
 */
@Entity
@Getter
@Setter
public class Product {

    /**
     * Уникальный идентификатор товара.
     * Генерируется автоматически при создании.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название товара.
     */
    private String name;

    /**
     * Производитель товара.
     * Указывает на компанию, выпустившую продукт.
     */
    private String manufacturer;

    /**
     * Категория товара.
     * Хранится в виде перечисления (Enum) для удобства работы с типами категорий.
     */
    @Enumerated(EnumType.STRING)
    private Category category;
}
