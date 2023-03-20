package com.example.iam.util;

public final class PasswordUtils {
    private static final int MIN_LENGTH = 8;
    public static final String SPECIAL_CHARACTERS = "!@#$%^&*()_+-=[]|,./?><";

    public static boolean isPasswordStrong(String password) {
        if (password.length() < MIN_LENGTH)
            return false;

        boolean containsUpperCase = false;
        boolean containsDigit = false;
        boolean containsSpecialChar = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                containsUpperCase = true;
            } else if (Character.isDigit(c)) {
                containsDigit = true;
            } else if (SPECIAL_CHARACTERS.indexOf(c) >= 0) {
                containsSpecialChar = true;
            }
        }

        return containsUpperCase && containsDigit && containsSpecialChar;
    }
}
