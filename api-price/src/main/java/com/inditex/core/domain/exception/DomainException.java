package com.inditex.core.domain.exception;

/**
 * Excepción base para errores de dominio.
 */
public abstract class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
