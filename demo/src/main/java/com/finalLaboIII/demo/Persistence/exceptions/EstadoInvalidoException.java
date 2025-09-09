package com.finalLaboIII.demo.Persistence.exceptions;

public class EstadoInvalidoException extends RuntimeException {
    public EstadoInvalidoException() {
        super();
    }
    public EstadoInvalidoException(String message) {
        super(message);
    }
}
