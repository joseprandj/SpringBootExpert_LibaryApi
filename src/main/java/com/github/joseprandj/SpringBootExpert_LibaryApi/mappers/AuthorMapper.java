package com.github.joseprandj.SpringBootExpert_LibaryApi.mappers;

import com.github.joseprandj.SpringBootExpert_LibaryApi.dto.AuthorDTO;
import com.github.joseprandj.SpringBootExpert_LibaryApi.dto.AuthorResponseDTO;
import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDTO toDTO(Author author);

    Author toEntity(AuthorDTO dto);

    @Mapping(source = "idApi", target = "id")
    AuthorResponseDTO toResponseDTO(Author author);
}
