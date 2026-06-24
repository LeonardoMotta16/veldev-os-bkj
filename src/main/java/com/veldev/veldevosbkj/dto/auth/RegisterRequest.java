package com.veldev.veldevosbkj.dto.auth;

import com.veldev.veldevosbkj.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "Nome e obrigatorio")
        @Size(max = 120, message = "Nome deve ter no maximo 120 caracteres")
        String nome,

        @NotBlank(message = "Email e obrigatorio")
        @Email(message = "Email deve ser valido")
        @Size(max = 160, message = "Email deve ter no maximo 160 caracteres")
        String email,

        @NotBlank(message = "Senha e obrigatoria")
        @Size(min = 8, max = 120, message = "Senha deve ter entre 8 e 120 caracteres")
        String senha,

        @NotNull(message = "Cargo e obrigatorio")
        Role cargo
) {
}
