package com.aviv.springbootdemo.webapi.helpers.exception;

import org.springframework.http.HttpStatus;

public class ExceptionClassInfo {
    private HttpStatus status;
    private String friendlyMessage;

    public ExceptionClassInfo(HttpStatus status, String friendlyMessage) {
        this.status = status;
        this.friendlyMessage = friendlyMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getFriendlyMessage() {
        return friendlyMessage;
    }

}
