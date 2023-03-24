package com.example.order.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.order.config.JwtProperties;
import com.example.order.service.JwtService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final JwtProperties properties;
    private JWTVerifier verifier;

    @PostConstruct
    protected void init() {
        Algorithm signAlgorithm = Algorithm.HMAC256(properties.getSecret());
        verifier = JWT.require(signAlgorithm)
                .withIssuer(properties.getIssuer())
                .withAudience(properties.getAudience())
                .build();
    }

    @Override
    public DecodedJWT validateToken(String token) throws JWTVerificationException {
        return verifier.verify(token);
    }

    @Override
    public List<String> extractRoles(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim("roles").asList(String.class);
    }

}