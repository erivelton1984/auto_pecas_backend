package com.br.autopecas.service;

import com.br.autopecas.dto.LoginRequest;
import com.br.autopecas.dto.RegisterRequest;
import com.br.autopecas.dto.AuthResponse;
import com.br.autopecas.model.User;
import com.br.autopecas.repository.UserRepository;
import com.br.autopecas.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UserRepository repository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public AuthService(UserRepository repository, JwtUtil jwtUtil, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
        this.encoder = encoder;
    }

    public AuthResponse register(RegisterRequest request) {

        // Verifica se email já existe
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole("CLIENT");           // role padrão
        user.setActive(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        repository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(token, user.getId(), user.getName(), user.getEmail(), user.getRole(), user.getActive());
    }

    public AuthResponse login(LoginRequest request) {

        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não encontrado"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha inválida");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(token, user.getId(), user.getName(), user.getEmail(), user.getRole(), user.getActive());
    }
}