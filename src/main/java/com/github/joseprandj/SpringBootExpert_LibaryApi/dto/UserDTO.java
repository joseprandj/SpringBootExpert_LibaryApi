package com.github.joseprandj.SpringBootExpert_LibaryApi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(
    @NotBlank
    String username,

    @NotBlank
    String password,

    String roles,

    @Email
    @NotBlank
    String email
) {

}
