package ru.test.reviews_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import ru.test.reviews_service.controller.ReviewController;
import ru.test.reviews_service.dto.NewReviewDto;
import ru.test.reviews_service.dto.ReviewShortDto;
import ru.test.reviews_service.dto.UpdateReviewDto;
import ru.test.reviews_service.entity.Review;
import ru.test.reviews_service.service.ReviewService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ReviewControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController)
                .setCustomArgumentResolvers(pageableResolver())
                .build();
    }

    private HandlerMethodArgumentResolver pageableResolver() {
        return new PageableHandlerMethodArgumentResolver();
    }

    @Test
    public void testGetReviewsByProductId() throws Exception {
        ReviewShortDto r1 = new ReviewShortDto();
        r1.setId(1L);
        r1.setComment("Отлично");
        ReviewShortDto r2 = new ReviewShortDto();
        r2.setId(2L);
        r2.setComment("Так себе");
        List<ReviewShortDto> reviews = Arrays.asList(r1, r2);

        when(reviewService.getReviewsByProductId(anyLong(), any(Pageable.class))).thenReturn(reviews);
        when(reviewService.getReviewsByProductId(eq(2L), any(Pageable.class))).thenReturn(List.of());

        mockMvc.perform(get("/api/reviews/product/1")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        mockMvc.perform(get("/api/reviews/product/2")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }


    @Test
    public void testGetReviewById() throws Exception {
        Review review = new Review();
        review.setId(1L);
        review.setComment("Вкусно!");

        when(reviewService.getReviewById(1L)).thenReturn(review);

        mockMvc.perform(get("/api/reviews/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comment").value("Вкусно!"));
    }

    @Test
    public void testCreateReview() throws Exception {
        NewReviewDto newReviewDto = new NewReviewDto();
        newReviewDto.setComment("Не очень вкусно!");
        newReviewDto.setEmail("email@mail.ru");
        newReviewDto.setRate(5);

        ReviewShortDto createdReview = new ReviewShortDto();
        createdReview.setId(1L);
        createdReview.setComment("Не очень вкусно!");
        createdReview.setEmail("email@mail.ru");
        createdReview.setRate(5);

        when(reviewService.createReview(anyLong(), any(NewReviewDto.class))).thenReturn(createdReview);

        mockMvc.perform(post("/api/reviews/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newReviewDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comment").value("Не очень вкусно!"));
    }

    @Test
    public void testUpdateReview() throws Exception {
        UpdateReviewDto updateReviewDto = new UpdateReviewDto();
        updateReviewDto.setComment("Обновленный текст отзыва");
        updateReviewDto.setRate(5);

        ReviewShortDto updatedReview = new ReviewShortDto();
        updatedReview.setId(1L);
        updatedReview.setComment("Обновленный текст отзыва");

        when(reviewService.updateReview(anyLong(), any(UpdateReviewDto.class))).thenReturn(updatedReview);

        mockMvc.perform(put("/api/reviews/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updateReviewDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comment").value("Обновленный текст отзыва"));
    }

    @Test
    public void testDeleteReview() throws Exception {
        mockMvc.perform(delete("/api/reviews/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
