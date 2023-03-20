package com.example.iam.service;

import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.List;

public interface JwtService {

    String generateAccessToken(String sub, List<String> roles);

    String generateIdToken(String sub, String email, String name);

    DecodedJWT verify(String token);
}
