package com.inditex.core.domain.exception;

/**
 * Excepción cuando no se encuentra un precio aplicable en el dominio.
 */
public class PriceNotFoundException extends DomainException {
    public PriceNotFoundException(String message) {
        super(message);
    }
}
