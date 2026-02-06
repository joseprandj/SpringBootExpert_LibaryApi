package com.github.joseprandj.SpringBootExpert_LibaryApi.dto;

import com.github.joseprandj.SpringBootExpert_LibaryApi.enums.GenderBook;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BookResponseDTO(
        UUID id,
        String isbn,
        String title,
        LocalDate publicatedDate,
        GenderBook genderBook,
        BigDecimal price,
        AuthorResponseDTO author
) {
}
