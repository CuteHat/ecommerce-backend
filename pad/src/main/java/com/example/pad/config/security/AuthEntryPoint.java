package com.example.pad.config.security;

@Slf4j
public class AuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.error("Auth entry point error: {}", authException.getMessage());

        int statusCode = response.getStatus() == HttpServletResponse.SC_FORBIDDEN ? HttpServletResponse.SC_FORBIDDEN : HttpServletResponse.SC_UNAUTHORIZED;
        response.setStatus(statusCode);
    }
}