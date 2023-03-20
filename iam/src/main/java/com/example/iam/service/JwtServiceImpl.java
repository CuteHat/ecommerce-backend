package com.example.iam.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.iam.config.JwtProperties;
import com.example.iam.util.JwtClaim;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final JwtProperties properties;
    private Algorithm signAlgorithm;
    private JWTVerifier verifier;

    @PostConstruct
    protected void init() {
        signAlgorithm = Algorithm.HMAC256(properties.getSecret());
        verifier = JWT.require(signAlgorithm).build();
    }

    public String generateAccessToken(String sub, List<String> permissions) {
        return JWT.create()
                .withSubject(sub)
                .withIssuer(properties.getIssuer())
                .withAudience(properties.getAudience())
                .withExpiresAt(Instant.now().plusSeconds(properties.getExpiration() * 60))
                .sign(signAlgorithm);
    }

    public String generateIdToken(String sub, String email, String name) {
        return JWT.create()
                .withSubject(sub)
                .withClaim(JwtClaim.EMAIL.name(), email)
                .withClaim(JwtClaim.NAME.name(), name)
                .withExpiresAt(Instant.now().plusSeconds(properties.getExpiration() * 60))
                .sign(signAlgorithm);
    }

    public DecodedJWT verify(String token) {
        return verifier.verify(token);
    }


}
