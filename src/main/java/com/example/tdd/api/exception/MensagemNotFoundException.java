package com.example.tdd.api.exception;

public class MensagemNotFoundException extends RuntimeException {
    public MensagemNotFoundException(String mensagemNaoEncontrada) {
        super(mensagemNaoEncontrada);
    }
}
