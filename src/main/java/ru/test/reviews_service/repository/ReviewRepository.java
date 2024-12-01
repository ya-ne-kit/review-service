package ru.test.reviews_service.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.test.reviews_service.entity.Review;

import java.util.List;

/**
 * Репозиторий для работы с сущностью отзыв.
 */
public interface ReviewRepository extends JpaRepository<Review, Long> {

    /**
     * Возвращает список отзывов по идентификатору товара с поддержкой пагинации.
     *
     * @param productId идентификатор товара, для которого необходимо получить отзывы.
     * @param pageable  объект, содержащий информацию о пагинации и сортировке.
     * @return список отзывов, связанных с указанным товаром.
     */
    List<Review> findByProductId(Long productId, Pageable pageable);
}