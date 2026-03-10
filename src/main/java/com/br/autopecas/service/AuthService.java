package com.br.autopecas.service;

import com.br.autopecas.dto.LoginRequest;
import com.br.autopecas.dto.RegisterRequest;
import com.br.autopecas.model.User;
import com.br.autopecas.repository.UserRepository;
import com.br.autopecas.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private JwtUtil jwtUtil;

    public AuthService(UserRepository repository) {
        this.repository = repository;
    }

    public String register(RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));

        repository.save(user);

        return jwtUtil.generateToken(user.getEmail());
    }

    public String login(LoginRequest request) {

        User user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

}