package com.aviv.springbootdemo.webapi.helpers.exception;

import java.util.Date;

public class ExceptionDetails {

    /**
     * User friendly message - for client use
     */
    private String userFriendlyErrorMessage;

    /**
     * Error message - the real error message, dev use only
     */
    private String errorMessage;

    /**
     * Stacktrace - the real stacktrace, dev use only
     */
    private String stacktrace;

    /**
     * Timestamp of the exception
     */
    private final Date timestamp;

    public ExceptionDetails(String userFriendlyErrorMessage, String errorMessage, String stacktrace, Date timestamp) {
        this.userFriendlyErrorMessage = userFriendlyErrorMessage;
        this.errorMessage = errorMessage;
        this.stacktrace = stacktrace;
        this.timestamp = timestamp;
    }

    public String getUserFriendlyErrorMessage() {
        return userFriendlyErrorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setUserFriendlyErrorMessage(String userFriendlyErrorMessage) {
        this.userFriendlyErrorMessage = userFriendlyErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }
}
