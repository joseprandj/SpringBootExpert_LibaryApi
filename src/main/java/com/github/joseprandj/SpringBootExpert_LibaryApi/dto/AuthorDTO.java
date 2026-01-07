package com.github.joseprandj.SpringBootExpert_LibaryApi.dto;

import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Author;

import java.time.LocalDate;

public record AuthorDTO(
    String name,
    LocalDate dtBirth,
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
