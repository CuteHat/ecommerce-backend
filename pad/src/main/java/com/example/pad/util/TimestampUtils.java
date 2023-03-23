package com.example.pad.util;

import java.sql.Timestamp;

public final class TimestampUtils {
    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }
}
