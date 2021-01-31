package br.com.gomining.gestaonotas.exception;

public class AlunoNotFoundException extends RuntimeException {
    public AlunoNotFoundException(String message) {
        super(message);
    }
}
