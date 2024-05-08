package com.example.crud.crudusuarios.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() { super("Email Em Uso !");}

    public EmailAlreadyExistsException(String message) { super(message);}
}
