package com.veldev.veldevosbkj.service;

import com.veldev.veldevosbkj.dto.auth.AuthResponse;
import com.veldev.veldevosbkj.dto.auth.LoginRequest;
import com.veldev.veldevosbkj.dto.auth.RegisterRequest;
import com.veldev.veldevosbkj.dto.auth.UsuarioResponse;
import com.veldev.veldevosbkj.entity.Usuario;
import com.veldev.veldevosbkj.exception.EmailAlreadyExistsException;
import com.veldev.veldevosbkj.exception.ResourceNotFoundException;
import com.veldev.veldevosbkj.mapper.UsuarioMapper;
import com.veldev.veldevosbkj.repository.UsuarioRepository;
import com.veldev.veldevosbkj.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UsuarioMapper usuarioMapper;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        String email = normalizeEmail(request.email());

        if (usuarioRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(email);
        }

        Usuario usuario = Usuario.builder()
                .nome(request.nome().trim())
                .email(email)
                .senha(passwordEncoder.encode(request.senha()))
                .cargo(request.cargo())
                .ativo(true)
                .build();

        Usuario savedUsuario = usuarioRepository.save(usuario);
        String token = jwtService.generateToken(savedUsuario);

        return usuarioMapper.toAuthResponse(savedUsuario, token);
    }

    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        normalizeEmail(request.email()),
                        request.senha()
                )
        );

        Usuario usuario = (Usuario) authentication.getPrincipal();
        String token = jwtService.generateToken(usuario);

        return usuarioMapper.toAuthResponse(usuario, token);
    }

    @Transactional(readOnly = true)
    public UsuarioResponse getAuthenticatedUser(String email) {
        Usuario usuario = usuarioRepository.findByEmail(normalizeEmail(email))
                .orElseThrow(() -> new ResourceNotFoundException("Usuario autenticado nao encontrado"));

        return usuarioMapper.toResponse(usuario);
    }

    private String normalizeEmail(String email) {
        return email.trim().toLowerCase();
    }
}
