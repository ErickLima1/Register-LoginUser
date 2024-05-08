package com.example.crud.crudusuarios.infra;

import com.example.crud.crudusuarios.exceptions.EmailAlreadyExistsException;
import com.example.crud.crudusuarios.exceptions.IdNotExistsException;
import com.example.crud.crudusuarios.exceptions.InvalidCredentialsException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestOperations;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.w3c.dom.events.EventException;

@ControllerAdvice
public class RestExcptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    private ResponseEntity<RestErrorMessage> EmailAlreadyExistsHandler(EmailAlreadyExistsException excption) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST, excption.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }

    @ExceptionHandler(IdNotExistsException.class)
    private ResponseEntity<RestErrorMessage>IdNotExistsHandler(IdNotExistsException excption) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, excption.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    private ResponseEntity<RestErrorMessage>InvalidCredentialsHandler(InvalidCredentialsException excption) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.UNAUTHORIZED, excption.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(threatResponse);
    }

}
