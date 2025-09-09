package com.finalLaboIII.demo.Persistence.exceptions;

public class DepartamentoNoEncontradoException extends RuntimeException {
    public DepartamentoNoEncontradoException() {
        super();
    }
    public DepartamentoNoEncontradoException(String message) {
        super(message);
    }
}
