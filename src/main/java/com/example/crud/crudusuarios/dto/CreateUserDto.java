package com.example.crud.crudusuarios.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDto(@NotNull(message = "Nome em Branco") @NotBlank(message = "Nome em Branco") String username,
                            @NotBlank(message = "Email em Branco") String email,
                            @NotBlank(message = "Senha em Branco ") String password) {

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}

