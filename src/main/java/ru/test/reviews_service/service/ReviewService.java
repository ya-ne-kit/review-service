package ru.test.reviews_service.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.test.reviews_service.dto.NewReviewDto;
import ru.test.reviews_service.dto.ReviewShortDto;
import ru.test.reviews_service.dto.UpdateReviewDto;
import ru.test.reviews_service.entity.Review;
import ru.test.reviews_service.repository.ReviewRepository;
import ru.test.reviews_service.util.ModelMapper;

import java.time.LocalDate;
import java.util.List;

/**
 * Сервисный класс для управления отзывами.
 * Класс предоставляет методы для выполнения основных операций с отзывами.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    /**
     * Получает отзыв по его идентификатору.
     *
     * @param id идентификатор отзыва.
     * @return отзыв с указанным идентификатором.
     * @throws EntityNotFoundException если отзыв с указанным идентификатором не найден.
     */
    public Review getReviewById(Long id) {
        log.info("INFO: find entity request T:Review ID:{{}}", id);
        return reviewRepository.findById(id).orElseGet(() -> {
            log.warn("EXC: entity not found T:Review ID:{{}}", id);
            throw new EntityNotFoundException(String.format("Отзыв с id = {%d} не существует", id));
        });
    }

    /**
     * Создает новый отзыв для указанного товара на основе переданного DTO.
     *
     * @param productId идентификатор товара, к которому относится отзыв.
     * @param dto      DTO, содержащий информацию о новом отзыве.
     * @return созданный отзыв в виде DTO.
     */
    public ReviewShortDto createReview(Long productId, NewReviewDto dto) {
        log.info("INFO: new entity request {{}}", dto.toString());
        Review review = modelMapper.toReview(dto);
        review.setProduct(productService.getProductById(productId));
        review.setDate(LocalDate.now());
        return modelMapper.toShortDto(reviewRepository.save(review));
    }

    /**
     * Обновляет существующий отзыв.
     *
     * @param id  идентификатор отзыва, который необходимо обновить.
     * @param dto DTO, содержащий новые данные для обновления отзыва.
     * @return обновленный отзыв в виде DTO.
     * @throws EntityNotFoundException если отзыв с указанным идентификатором не найден.
     */
    public ReviewShortDto updateReview(Long id, UpdateReviewDto dto) {
        log.info("INFO: update entity request T:Review ID:{{}} Data:{{}}", id, dto.toString());
        Review review = getReviewById(id);
        modelMapper.updateReview(dto, review);
        return modelMapper.toShortDto(reviewRepository.save(review));
    }

    /**
     * Удаляет отзыв по его идентификатору.
     *
     * @param id идентификатор отзыва, который необходимо удалить.
     */
    public void deleteReview(Long id) {
        log.info("INFO: remove entity request T:Review ID:{{}}", id);
        reviewRepository.deleteById(id);
    }

    /**
     * Получает список отзывов для указанного товара с поддержкой пагинации.
     *
     * @param productId идентификатор товара, для которого нужно получить отзывы.
     * @param pageable  объект, содержащий информацию о пагинации и сортировке.
     * @return список кратких представлений отзывов для указанного товара в соответствии с параметрами пагинации.
     */
    public List<ReviewShortDto> getReviewsByProductId(Long productId, Pageable pageable) {
        log.info("INFO: find all entities request T:Review ID_Product:{{}} {{}}", productId, pageable.toString());
        productService.getProductById(productId);
        return reviewRepository.findByProductId(productId, pageable).stream().map(modelMapper::toShortDto).toList();
    }
}
