package com.example.lewjun.exception;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(final String msg, final Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(final String msg) {
        super(msg);
    }

    public ValidateCodeException() {
        this("validate code failure");
    }
}
