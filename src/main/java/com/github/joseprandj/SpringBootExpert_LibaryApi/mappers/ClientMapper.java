package com.github.joseprandj.SpringBootExpert_LibaryApi.mappers;

import com.github.joseprandj.SpringBootExpert_LibaryApi.dto.ClientDTO;
import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDTO toDTO(Client client);

    Client toEntity(ClientDTO dto);
}
