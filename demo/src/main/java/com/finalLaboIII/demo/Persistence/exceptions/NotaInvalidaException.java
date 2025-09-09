package com.finalLaboIII.demo.Persistence.exceptions;

public class NotaInvalidaException extends RuntimeException {
    public NotaInvalidaException() {
        super();
    }
    public NotaInvalidaException(String message) {
        super(message);
    }
}
