package com.example.crud.crudusuarios.exceptions;

public class IdNotExistsException extends RuntimeException {
    public IdNotExistsException() { super("Usuário Não Existe"); }

    public IdNotExistsException(String message) {super(message);}
}
