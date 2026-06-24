package com.veldev.veldevosbkj.security;

import com.veldev.veldevosbkj.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {

        System.out.println("Buscando usuario: " + username);

        return usuarioRepository.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario nao encontrado"));
    }
}
