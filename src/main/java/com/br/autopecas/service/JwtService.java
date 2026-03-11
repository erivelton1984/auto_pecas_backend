package com.br.autopecas.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String email){

        return Jwts.builder()
                .setSubject(email)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

    }
}