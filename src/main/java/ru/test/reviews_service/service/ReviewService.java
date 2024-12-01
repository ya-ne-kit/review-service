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

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public Review getReviewById(Long id) {
        log.info("INFO: find entity request T:Review ID:{{}}", id);
        return reviewRepository.findById(id).orElseGet(() -> {
            log.warn("EXC: entity not found T:Review ID:{{}}", id);
            throw new EntityNotFoundException(String.format("Отзыв с id = {%d} не существует", id));
        });
    }

    public ReviewShortDto createReview(Long productId, NewReviewDto dto) {
        log.info("INFO: new entity request {{}}", dto.toString());
        Review review = modelMapper.toReview(dto);
        review.setProduct(productService.getProductById(productId));
        review.setDate(LocalDate.now());
        return modelMapper.toShortDto(reviewRepository.save(review));
    }

    public ReviewShortDto updateReview(Long id, UpdateReviewDto dto) {
        log.info("INFO: update entity request T:Review ID:{{}} Data:{{}}", id, dto.toString());
        Review review = getReviewById(id);
        modelMapper.updateReview(dto, review);
        return modelMapper.toShortDto(reviewRepository.save(review));
    }

    public void deleteReview(Long id) {
        log.info("INFO: remove entity request T:Review ID:{{}}", id);
        reviewRepository.deleteById(id);
    }

    public List<ReviewShortDto> getReviewsByProductId(Long productId, Pageable pageable) {
        log.info("INFO: find all entities request T:Review ID_Product:{{}} {{}}", productId, pageable.toString());
        productService.getProductById(productId);
        return reviewRepository.findByProductId(productId, pageable).stream().map(modelMapper::toShortDto).toList();
    }
}
