package com.github.joseprandj.SpringBootExpert_LibaryApi.mappers;

import com.github.joseprandj.SpringBootExpert_LibaryApi.dto.UserDTO;
import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDTO dto);
}
