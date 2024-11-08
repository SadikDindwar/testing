package com.apiexamples.exception;

import com.apiexamples.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    //This will handle only ResourceNotFound Exception
    @ExceptionHandler(ResourceNotFound.class)  //Any Exception that is of ResourceNotFound will come here
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
            ResourceNotFound ex
    ){

    ErrorDetails err = new ErrorDetails(ex.getMessage() , new Date());
     //Here we are creating a new ErrorDetails object and passing the message and date to it and returning this object
     // along with HttpStatus code.
    return new ResponseEntity<>(err , HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //This will handle every Exception  because Exception is the parent class of every Exceptions.
    @ExceptionHandler(Exception.class)  //Any Exception that is of ResourceNotFound will come here
    public ResponseEntity<ErrorDetails> handleException(
            Exception ex
    ){
        ErrorDetails err = new ErrorDetails(ex.getMessage() , new Date());
        return new ResponseEntity<>(err , HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
