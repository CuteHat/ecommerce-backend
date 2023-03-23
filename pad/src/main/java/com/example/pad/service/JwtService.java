package com.example.pad.service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public interface JwtValidatorService {
    DecodedJWT validateToken(String token) throws JWTVerificationException;
}
