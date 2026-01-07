package com.github.joseprandj.SpringBootExpert_LibaryApi.dto;

import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Author;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorResponseDTO (
    UUID id,
    String name,
    LocalDate dtBirth,
    String nationality
){
    public static AuthorResponseDTO authorResponseDto(Author author){
        return new AuthorResponseDTO(
            author.getIdApi(),
            author.getName(),
            author.getDtBirth(),
            author.getNationality()
        );
    }
}
