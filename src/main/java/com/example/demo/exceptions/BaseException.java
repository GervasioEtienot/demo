package com.example.demo.exceptions;

import java.util.Date;

public abstract class BaseException extends RuntimeException {

    // private String message;
    private Date timestamp;

    protected BaseException() {
        super();
    }

    protected BaseException(String msg) {
        super(msg);
        this.timestamp = new Date();
        this.timestamp = new Date();
        // this.message = msg;
    }

    protected BaseException(String message, Throwable throwable) {
        super(message, throwable);
        this.timestamp = new Date();
    }

    // protected String getMessage() {
    //     return message;
    // }

    protected Date getTimestamp() {
        return timestamp;
    }
}
