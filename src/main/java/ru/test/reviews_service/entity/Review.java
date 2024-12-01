package ru.test.reviews_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String comment;
    private String pros;
    private String cons;
    private Integer rate;
    private String email;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}