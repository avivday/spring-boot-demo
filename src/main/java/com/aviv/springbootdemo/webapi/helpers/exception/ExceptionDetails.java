package com.aviv.springbootdemo.webapi.helpers.exception;

import java.util.Date;

public class ExceptionDetails {
    private String message;
    private String details;
    private Throwable cause;

    private final Date timestamp;

    public ExceptionDetails(String message, String details, Date timestamp, Throwable cause) {
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
        this.cause = cause;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public Date getTimestamp() {
        return timestamp;
    }
    public Throwable getCause() {
        return this.cause;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }
}
