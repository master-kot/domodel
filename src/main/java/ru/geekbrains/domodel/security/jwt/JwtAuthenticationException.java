package ru.geekbrains.domodel.security.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * Исключение ошибки авторизации.
 */
public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
