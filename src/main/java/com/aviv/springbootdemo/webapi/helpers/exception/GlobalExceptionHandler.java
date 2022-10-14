package com.aviv.springbootdemo.webapi.helpers.exception;

import com.aviv.springbootdemo.webapi.AppSettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.ws.rs.NotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private AppSettings appSettings;

    private Map<Class, ExceptionClassInfo> exceptionToInfo = new HashMap<>()
    {{
        put(NotFoundException.class, new ExceptionClassInfo(HttpStatus.NOT_FOUND, "Record not found"));
        put(MethodArgumentTypeMismatchException.class, new ExceptionClassInfo(HttpStatus.BAD_REQUEST, "Bad Request"));
        put(Exception.class, new ExceptionClassInfo(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"));
    }};;

    public GlobalExceptionHandler(AppSettings appSettings) {
        this.appSettings = appSettings;
    }

    // handle global exceptions
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ExceptionDetails> handleInternalServer(Exception exception, WebRequest request) {
        ExceptionClassInfo exceptionClassInfo = this.exceptionToInfo.get(exception.getClass());
        if(exceptionClassInfo == null) {
            exceptionClassInfo = this.exceptionToInfo.get(Exception.class);
        }

        ExceptionDetails exceptionDetails = new ExceptionDetails(exception.getMessage(), request.getDescription(false), new Date(), exception.getCause());
        if (!this.appSettings.showDetailedExceptions()) {
            // Don't show detailed error for certain envs
            exceptionDetails.setMessage(exceptionClassInfo.getFriendlyMessage());
            exceptionDetails.setCause(null);
        }

        return new ResponseEntity<>(exceptionDetails, exceptionClassInfo.getStatus());    }
}
