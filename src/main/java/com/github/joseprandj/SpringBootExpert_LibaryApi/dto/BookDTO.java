package com.github.joseprandj.SpringBootExpert_LibaryApi.dto;

import com.github.joseprandj.SpringBootExpert_LibaryApi.enums.GenderBook;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BookDTO(
        @ISBN
        @NotBlank
        String isbn,

        @NotBlank
        String title,

        @NotNull
        @Past
        LocalDate publicatedDate,
        GenderBook genderBook,
        BigDecimal price,

        @NotNull
        UUID idAuthor
) {
}
