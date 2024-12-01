package ru.test.reviews_service.util;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.test.reviews_service.dto.*;
import ru.test.reviews_service.entity.Product;
import ru.test.reviews_service.entity.Review;

@Mapper(componentModel = "spring")
public abstract class ModelMapper {
    public abstract ReviewShortDto toShortDto(Review review);
    public abstract Review toReview(NewReviewDto dto);
    public abstract void updateReview(UpdateReviewDto dto, @MappingTarget Review model);

    public abstract Product toProduct(NewProductDto dto);
    public abstract void updateProduct(UpdateProductDto dto, @MappingTarget Product model);
}
