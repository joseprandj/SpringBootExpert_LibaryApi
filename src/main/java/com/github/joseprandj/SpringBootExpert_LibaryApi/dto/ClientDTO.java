package com.github.joseprandj.SpringBootExpert_LibaryApi.dto;

import jakarta.validation.constraints.NotBlank;

public record ClientDTO(
        @NotBlank
        String clientId,

        @NotBlank
        String clientSecrect,

        @NotBlank
        String redirectUri,

        String scope
) {
}
