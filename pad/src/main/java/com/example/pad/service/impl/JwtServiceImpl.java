package com.example.pad.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.pad.config.JwtProperties;
import com.example.pad.service.JwtValidatorService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtValidatorServiceImpl implements JwtValidatorService {
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

//    public boolean hasAdminRole(DecodedJWT decodedJWT) {
//        return decodedJWT.getClaim("role").asString().equalsIgnoreCase("administrator");
//    }

}