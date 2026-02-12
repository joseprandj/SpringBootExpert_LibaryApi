package com.github.joseprandj.SpringBootExpert_LibaryApi.controller;

import com.github.joseprandj.SpringBootExpert_LibaryApi.dto.ClientDTO;
import com.github.joseprandj.SpringBootExpert_LibaryApi.mappers.ClientMapper;
import com.github.joseprandj.SpringBootExpert_LibaryApi.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService service;
    private final ClientMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public void save(@RequestBody ClientDTO dto){
        service.save(mapper.toEntity(dto));
    }

}
