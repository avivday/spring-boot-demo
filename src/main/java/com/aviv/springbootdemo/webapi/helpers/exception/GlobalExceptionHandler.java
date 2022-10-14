package com.aviv.springbootdemo.webapi.helpers.exception;

import com.aviv.springbootdemo.webapi.AppSettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    private AppSettings appSettings;

    public GlobalExceptionHandler(AppSettings appSettings) {
        this.appSettings = appSettings;
    }

    // handle global exceptions
    @ExceptionHandler( { Exception.class })
    public ResponseEntity<ExceptionDetails> handleGlobalException(Exception exception, WebRequest request) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(exception.getMessage(),  request.getDescription(false), new Date(), exception.getCause());
        if(!this.appSettings.showDetailedExceptions()) {
            exceptionDetails.setMessage("Some Friendly Message Here");
            exceptionDetails.setCause(null);
        }

        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
