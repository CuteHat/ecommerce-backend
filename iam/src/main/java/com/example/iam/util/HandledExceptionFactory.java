package com.example.iam.util;

import com.example.iam.exception.HandledException;
import com.example.iam.exception.detail.HandledExceptionDetail;
import org.springframework.http.HttpStatus;

public final class HandledExceptionFactory {

    public static HandledException getHandledException(String message) {
        return getHandledException(HttpStatus.BAD_REQUEST, message);
    }

    public static HandledException getHandledException(HttpStatus status, String message) {
        return new HandledException(status, new HandledExceptionDetail(message));
    }
}
