package com.example.iam.util;

import com.example.iam.config.security.JwtAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static Long getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication instanceof JwtAuthenticationToken jwtAuthentication) {
            String principal = (String) jwtAuthentication.getPrincipal();
            return Long.parseLong(principal);
        }
        return null;
    }
}
