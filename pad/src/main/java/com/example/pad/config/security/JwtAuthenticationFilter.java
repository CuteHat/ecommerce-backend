package com.example.pad.config.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.pad.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extractJwtToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        if (StringUtils.hasText(token)) {
            try {
                DecodedJWT decodedJWT = jwtService.validateToken(token);
                List<String> roleNames = jwtService.extractRoles(decodedJWT);
                JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(
                        decodedJWT.getSubject(),
                        generateAuthorities(roleNames));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
                log.error("JWT token verification failed", e);
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid access token");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private String extractJwtToken(String authorizationHeader) {
        return StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")
                ? authorizationHeader.substring(7) : null;
    }

    private List<SimpleGrantedAuthority> generateAuthorities(List<String> roleNames) {
        return roleNames.stream()
                .map(roleName -> new SimpleGrantedAuthority("ROLE_" + roleName.toUpperCase()))
                .collect(Collectors.toList());
    }
}
