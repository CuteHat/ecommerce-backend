package com.example.iam.util;

public enum JwtClaim {
    NAME("name"),
    EMAIL("email");

    final String value;

    JwtClaim(String value) {
        this.value = value;
    }
}
