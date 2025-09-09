package com.finalLaboIII.demo.Persistence.exceptions;

public class MateriaNoEncontradaException extends RuntimeException {
    public MateriaNoEncontradaException() {
        super();
    }
    public MateriaNoEncontradaException(String message) {
        super(message);
    }
}
