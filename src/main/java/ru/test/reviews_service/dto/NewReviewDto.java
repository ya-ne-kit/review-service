package ru.test.reviews_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@ToString
public class NewReviewDto {
    @NotBlank(message = "Содержание отзыва не может быть пустым")
    private String comment;
    private String pros;
    private String cons;
    @Min(message = "Минимальное значение оценки - 1", value = 1)
    @Max(message = "Минимальное значение оценки - 5", value = 5)
    private Integer rate;
    @Email(message = "Некорректный формат email")
    private String email;
}
