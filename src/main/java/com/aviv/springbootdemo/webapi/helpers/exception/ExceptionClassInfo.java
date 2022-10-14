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

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getFriendlyMessage() {
        return friendlyMessage;
    }

    public void setFriendlyMessage(String friendlyMessage) {
        this.friendlyMessage = friendlyMessage;
    }
}
