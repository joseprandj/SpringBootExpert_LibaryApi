package com.github.joseprandj.SpringBootExpert_LibaryApi.mappers;

import com.github.joseprandj.SpringBootExpert_LibaryApi.dto.BookDTO;
import com.github.joseprandj.SpringBootExpert_LibaryApi.dto.BookResponseDTO;
import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Book;
import com.github.joseprandj.SpringBootExpert_LibaryApi.repository.AuthorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = AuthorMapper.class)
public abstract class BookMapper {
    @Autowired
    AuthorRepository authorRepository;

    @Mapping(target = "author", expression = "java(authorRepository.findByIdApi(dto.idAuthor()).orElse(null))")
    public abstract Book toEntity(BookDTO dto);

    @Mapping(source = "idApi", target = "id")
    public abstract BookResponseDTO toDTO(Book book);
}
