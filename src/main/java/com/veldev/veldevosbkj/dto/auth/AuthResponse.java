package com.veldev.veldevosbkj.dto.auth;

import com.veldev.veldevosbkj.enums.Role;

public record AuthResponse(
        String token,
        String tipo,
        Long id,
        String nome,
        String email,
        Role cargo
) {
}
