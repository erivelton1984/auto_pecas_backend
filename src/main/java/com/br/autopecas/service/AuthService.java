package com.br.autopecas.service;

import com.br.autopecas.dto.LoginDTO;
import com.br.autopecas.model.User;
import com.br.autopecas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final JwtService jwt;

    public String login(LoginDTO dto){

        User user = repository.findByEmail(dto.getEmail())
                .orElseThrow();

        return jwt.generateToken(user.getEmail());

    }

}
