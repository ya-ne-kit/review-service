package ru.test.reviews_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.test.reviews_service.entity.Product;

/**
 * Репозиторий для работы с сущностью товар.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}