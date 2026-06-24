package com.veldev.veldevosbkj.dto.auth;

import com.veldev.veldevosbkj.enums.Role;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        Role cargo
) {
}
