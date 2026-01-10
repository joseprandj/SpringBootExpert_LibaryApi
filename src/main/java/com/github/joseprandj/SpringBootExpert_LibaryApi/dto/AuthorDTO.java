package com.github.joseprandj.SpringBootExpert_LibaryApi.dto;

import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AuthorDTO(
    @NotBlank
    @Size(max = 100)
    String name,

    @NotNull
    @Past
    LocalDate dtBirth,

    @NotBlank
    @Size(min = 2, max = 50)
    String nationality
) {

    public Author authorDtoToAuthor(){
        Author author = new Author();
        author.setName(this.name);
        author.setDtBirth(this.dtBirth);
        author.setNationality(this.nationality);
        return author;
    }
}
