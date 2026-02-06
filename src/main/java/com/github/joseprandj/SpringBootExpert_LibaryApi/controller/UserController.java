package com.github.joseprandj.SpringBootExpert_LibaryApi.controller;

import com.github.joseprandj.SpringBootExpert_LibaryApi.dto.UserDTO;
import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.User;
import com.github.joseprandj.SpringBootExpert_LibaryApi.mappers.UserMapper;
import com.github.joseprandj.SpringBootExpert_LibaryApi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody UserDTO dto){
        User teste = mapper.toEntity(dto);
        service.save(teste);
    }

}
