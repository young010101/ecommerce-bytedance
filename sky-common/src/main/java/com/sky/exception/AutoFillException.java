package com.sky.exception;

/**
 * 自动填充异常
 */
public class AutoFillException extends RuntimeException {

    public AutoFillException(String message) {
        super(message);
    }

    public AutoFillException(String message, Throwable cause) {
        super(message, cause);
    }
}
