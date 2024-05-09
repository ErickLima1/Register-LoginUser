package com.example.crud.crudusuarios.exceptions;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException() {
        super("Email ou senha incorretos!");
    }

    public InvalidCredentialsException(String message) { super(message);}
}
