package com.finalLaboIII.demo.Persistence.exceptions;

public class AsignaturaYaExisteException extends RuntimeException {
    public AsignaturaYaExisteException() {
        super();
    }
    public AsignaturaYaExisteException(String message) {
        super(message);
    }
}
