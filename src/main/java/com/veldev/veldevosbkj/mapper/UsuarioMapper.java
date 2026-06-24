package com.veldev.veldevosbkj.mapper;

import com.veldev.veldevosbkj.dto.auth.AuthResponse;
import com.veldev.veldevosbkj.dto.auth.UsuarioResponse;
import com.veldev.veldevosbkj.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCargo()
        );
    }

    public AuthResponse toAuthResponse(Usuario usuario, String token) {
        return new AuthResponse(
                token,
                "Bearer",
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCargo()
        );
    }
}
