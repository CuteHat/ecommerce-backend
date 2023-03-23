package com.example.pad.service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.List;

public interface JwtService {
    DecodedJWT validateToken(String token) throws JWTVerificationException;

    List<String> extractRoles(DecodedJWT decodedJWT);
}
