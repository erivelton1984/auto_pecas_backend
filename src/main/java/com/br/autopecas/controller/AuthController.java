package com.br.autopecas.controller;

import com.br.autopecas.dto.AuthResponse;
import com.br.autopecas.dto.LoginRequest;
import com.br.autopecas.dto.RegisterRequest;
import com.br.autopecas.service.AuthService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {

        String token = service.register(request);

        return new AuthResponse(token);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        String token = service.login(request);

        return new AuthResponse(token);
    }

}
