package ru.test.reviews_service.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import ru.test.reviews_service.util.Category;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String manufacturer;

    @Enumerated(EnumType.STRING)
    private Category category;
}