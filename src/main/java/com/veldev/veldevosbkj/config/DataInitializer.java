package com.veldev.veldevosbkj.config;

import com.veldev.veldevosbkj.entity.Usuario;
import com.veldev.veldevosbkj.enums.Role;
import com.veldev.veldevosbkj.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {

        if (usuarioRepository.count() == 0) {

            Usuario usuario = new Usuario();

            usuario.setNome("Leonardo");
            usuario.setEmail("admin@veldev.com");
            usuario.setSenha(passwordEncoder.encode("123456"));
            usuario.setCargo(Role.ADMIN);   // ← faltava
            usuario.setAtivo(true);         // ← faltava

            usuarioRepository.save(usuario);

            System.out.println("=== USUARIO ADMIN CRIADO ===");
            System.out.println("Email: admin@veldev.com");
            System.out.println("Senha: 123456");
        }
    }
}