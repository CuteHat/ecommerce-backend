package com.example.iam.util;

public enum JwtClaim {
    NAME("name"),
    EMAIL("email"),
    ROLES("roles");

    public final String value;

    JwtClaim(String value) {
        this.value = value;
    }
}
