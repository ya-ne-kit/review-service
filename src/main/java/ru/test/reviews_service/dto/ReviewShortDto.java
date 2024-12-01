package ru.test.reviews_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ReviewShortDto {
    private Long id;
    private LocalDate date;
    private String comment;
    private String pros;
    private String cons;
    private Integer rate;
    private String email;
}
