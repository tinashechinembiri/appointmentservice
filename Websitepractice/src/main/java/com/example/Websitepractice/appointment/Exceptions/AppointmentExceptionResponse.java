package com.example.Websitepractice.appointment.Exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class AppointmentExceptionResponse {

    private final  String message;
    private final  Throwable throwable;
    private  final HttpStatus status;
    private  final ZonedDateTime timestamp;

    public AppointmentExceptionResponse(final String message, final Throwable throwable, final HttpStatus status, final ZonedDateTime timestamp) {
        this.message = message;
        this.throwable = throwable;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return this.message;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }
}
