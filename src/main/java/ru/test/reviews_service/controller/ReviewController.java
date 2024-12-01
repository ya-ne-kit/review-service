package ru.test.reviews_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import ru.test.reviews_service.dto.NewReviewDto;
import ru.test.reviews_service.dto.ReviewShortDto;
import ru.test.reviews_service.dto.UpdateReviewDto;
import ru.test.reviews_service.entity.Review;
import ru.test.reviews_service.service.ReviewService;

import java.util.List;

/**
 * Контроллер для управления отзывами.
 * <p>
 * Контроллер предоставляет RESTful API для выполнения основных операций с отзывами.
 * </p>
 */
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    /**
     * Получает список отзывов по идентификатору товара с поддержкой пагинации.
     *
     * @param productId идентификатор товара, для которого нужно получить отзывы.
     * @param pageable  объект Pageable для настройки пагинации.
     * @param page      номер страницы (необязательный параметр).
     * @param size      размер страницы (необязательный параметр).
     * @return список кратких данных отзывов для указанного продукта на текущей странице.
     */
    @GetMapping("/product/{productId}")
    public List<ReviewShortDto> getReviewsByProductId(@PathVariable Long productId,
                                                      @PageableDefault Pageable pageable,
                                                      @RequestParam(required = false) Integer page,
                                                      @RequestParam(required = false) Integer size) {
        if (page != null && size != null) {
            pageable = PageRequest.of(page, size);
        }
        return reviewService.getReviewsByProductId(productId, pageable);
    }

    /**
     * Получает отзыв по его идентификатору.
     *
     * @param id идентификатор отзыва.
     * @return отзыв с указанным идентификатором.
     */
    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    /**
     * Создает новый отзыв для указанного товара.
     *
     * @param productId идентификатор товара, для которого создается отзыв.
     * @param dto      DTO для создания нового отзыва.
     * @return созданный краткий DTO отзыва.
     */
    @PostMapping("/{productId}")
    public ReviewShortDto createReview(@PathVariable Long productId, @Valid @RequestBody NewReviewDto dto) {
        return reviewService.createReview(productId, dto);
    }

    /**
     * Обновляет существующий отзыв.
     *
     * @param id  идентификатор отзыва, который нужно обновить.
     * @param dto DTO для обновления отзыва.
     * @return обновленный краткий DTO отзыва.
     */
    @PutMapping("/{id}")
    public ReviewShortDto updateReview(@PathVariable Long id, @Valid @RequestBody UpdateReviewDto dto) {
        return reviewService.updateReview(id, dto);
    }

    /**
     * Удаляет отзыв по его идентификатору.
     *
     * @param id идентификатор отзыва, который нужно удалить.
     */
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
}
