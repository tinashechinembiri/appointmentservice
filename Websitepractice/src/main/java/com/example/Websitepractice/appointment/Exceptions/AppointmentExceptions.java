package com.example.Websitepractice.appointment.Exceptions;

public class AppointmentExceptions  extends  RuntimeException{


    public AppointmentExceptions(final String message) {
        super(message);
    }

    public AppointmentExceptions(final String message, final Throwable cause) {
        super(message, cause);
    }

}
