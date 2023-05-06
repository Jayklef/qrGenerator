package com.jayklef.qrgenerator.exception;

import com.jayklef.qrgenerator.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> ResourceNotFoundException(ResourceNotFoundException exception,
                                                                  WebRequest webRequest){
        ErrorMessage message = new ErrorMessage();
        message.setStatusCode(HttpStatus.NOT_FOUND.value());
        message.setMessage(exception.getMessage());
        message.setTimestamp(new Date());

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ItemAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> ItemAlreadyExistException(ItemAlreadyExistsException exception,
                                                                  WebRequest webRequest){
        ErrorMessage message = new ErrorMessage();
        message.setStatusCode(HttpStatus.CONFLICT.value());
        message.setMessage(exception.getMessage());
        message.setTimestamp(new Date());

        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }
}
