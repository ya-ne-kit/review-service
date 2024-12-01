package ru.test.reviews_service.util;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.test.reviews_service.dto.*;
import ru.test.reviews_service.entity.Product;
import ru.test.reviews_service.entity.Review;

/**
 * Утилитный класс для преобразования сущностей и DTO с использованием MapStruct.
 * Класс предоставляет методы для конвертации между сущностями Review и Product и соответствующими DTO.
 */
@Mapper(componentModel = "spring")
public abstract class ModelMapper {

    /**
     * Преобразует сущность отзыва в DTO короткого формата.
     *
     * @param review сущность отзыва, которую необходимо преобразовать.
     * @return DTO короткого формата, представляющее отзыв.
     */
    public abstract ReviewShortDto toShortDto(Review review);

    /**
     * Преобразует DTO нового отзыва в сущность отзыва.
     *
     * @param dto DTO нового отзыва, которое необходимо преобразовать.
     * @return сущность отзыва, созданная на основе предоставленного DTO.
     */
    public abstract Review toReview(NewReviewDto dto);

    /**
     * Обновляет существующую сущность отзыва на основе предоставленного DTO.
     *
     * @param dto  DTO обновления отзыва, содержащее новые данные.
     * @param model существующая сущность отзыва, которую необходимо обновить.
     */
    public abstract void updateReview(UpdateReviewDto dto, @MappingTarget Review model);

    /**
     * Преобразует DTO нового товара в сущность товар.
     *
     * @param dto DTO нового товара, которое необходимо преобразовать.
     * @return сущность товар, созданная на основе предоставленного DTO.
     */
    public abstract Product toProduct(NewProductDto dto);

    /**
     * Обновляет существующую сущность товар на основе предоставленного DTO.
     *
     * @param dto  DTO обновления товара, содержащее новые данные.
     * @param model существующая сущность товар, которую необходимо обновить.
     */
    public abstract void updateProduct(UpdateProductDto dto, @MappingTarget Product model);
}
