package com.example.Websitepractice.appointment.Exceptions;

import org.springframework.http.HttpStatus;

public class AppointmentCustomError extends  RuntimeException {
    private HttpStatus status = null;

    private Object data = null;

    public  AppointmentCustomError(String Message )
    {
        super(Message);
    }
    public  AppointmentCustomError(HttpStatus status , String message )
    {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public void setStatus(final HttpStatus status) {
        this.status = status;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(final Object data) {
        this.data = data;
    }
}
