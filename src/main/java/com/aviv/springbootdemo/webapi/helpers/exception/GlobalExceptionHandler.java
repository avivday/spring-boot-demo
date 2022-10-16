package com.aviv.springbootdemo.webapi.helpers.exception;

import com.aviv.springbootdemo.webapi.AppSettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private AppSettings appSettings;

    /**
     * Map exception to exception info.
     */
    private Map<Class, ExceptionClassInfo> exceptionToInfo = new HashMap<>()
    {{
        put(ForbiddenException.class, new ExceptionClassInfo(HttpStatus.FORBIDDEN, "Access Forbidden"));
        put(NotAuthorizedException.class, new ExceptionClassInfo(HttpStatus.UNAUTHORIZED, "Unauthorized"));
        put(BindException.class, new ExceptionClassInfo(HttpStatus.UNPROCESSABLE_ENTITY, "Unprocessable Entity"));
        put(NotFoundException.class, new ExceptionClassInfo(HttpStatus.NOT_FOUND, "Record not found"));
        put(MethodArgumentTypeMismatchException.class, new ExceptionClassInfo(HttpStatus.BAD_REQUEST, "Bad Request"));
        put(Exception.class, new ExceptionClassInfo(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"));
    }};;

    public GlobalExceptionHandler(AppSettings appSettings) {
        this.appSettings = appSettings;
    }

    /**
     * Handle global exceptions according to env vars and exception mapping
     * @param exception the exception
     * @param request the actual request
     * @return custom made exception
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ExceptionDetails> handleExceptions(Exception exception, WebRequest request) {
        ExceptionClassInfo exceptionClassInfo = this.exceptionToInfo.get(exception.getClass());
        if(exceptionClassInfo == null) {
            exceptionClassInfo = this.exceptionToInfo.get(Exception.class);
        }

        String stacktrace = Arrays.stream(exception.getStackTrace())
                .map(s->s.toString())
                .collect(Collectors.joining("\n"));
        ExceptionDetails exceptionDetails = new ExceptionDetails(exceptionClassInfo.getFriendlyMessage(), exception.getMessage(), stacktrace, new Date());

        if (!this.appSettings.showDetailedExceptions()) {
            // Don't show detailed error for certain envs - error message and stacktrace are for dev use only
            exceptionDetails.setErrorMessage(null);
            exceptionDetails.setStacktrace(null);
        }

        return new ResponseEntity<>(exceptionDetails, exceptionClassInfo.getStatus());
    }

}
