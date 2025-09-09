package com.finalLaboIII.demo.Persistence.exceptions;

public class AlumnoNoEncontradoException extends RuntimeException {
    public AlumnoNoEncontradoException() {
        super();
    }
    public AlumnoNoEncontradoException(String message) {
        super(message);
    }
}