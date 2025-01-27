package com.example.demo.exceptions;

public class OperacaoNaoPermitidaException extends RuntimeException {
    public OperacaoNaoPermitidaException(String message) {
        super(message);
    }
}
