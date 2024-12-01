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

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/product/{productId}")
    public List<ReviewShortDto> getReviewsByProductId(@PathVariable Long productId,
                                                      @PageableDefault(size = 10) Pageable pageable,
                                                      @RequestParam(required = false) Integer page,
                                                      @RequestParam(required = false) Integer size) {
        if (page != null && size != null) {
            pageable = PageRequest.of(page, size);
        }
        return reviewService.getReviewsByProductId(productId, pageable);
    }

    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    @PostMapping("/{productId}")
    public ReviewShortDto createReview(@PathVariable Long productId, @Valid @RequestBody NewReviewDto dto) {
        return reviewService.createReview(productId, dto);
    }

    @PutMapping("/{id}")
    public ReviewShortDto updateReview(@PathVariable Long id, @Valid @RequestBody UpdateReviewDto dto) {
        return reviewService.updateReview(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
}
