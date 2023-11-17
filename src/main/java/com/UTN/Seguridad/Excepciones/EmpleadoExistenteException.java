package com.UTN.Seguridad.Excepciones;

public class EmpleadoExistenteException extends RuntimeException {

    public EmpleadoExistenteException() {
        super();
    }

    public EmpleadoExistenteException(String message) {
        super(message);
    }

    public EmpleadoExistenteException(String message, Throwable cause) {
        super(message, cause);
    }
}
