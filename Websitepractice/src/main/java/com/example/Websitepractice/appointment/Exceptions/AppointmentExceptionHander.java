package com.example.Websitepractice.appointment.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class AppointmentExceptionHander {
    @ExceptionHandler(value =  {AppointmentExceptions.class})
    public ResponseEntity<Object> handleAppointmentExpections(AppointmentExceptions ex)
    {
        HttpStatus badrequest = HttpStatus.BAD_REQUEST;
        AppointmentExceptionResponse appointmentExceptionResponse =   new AppointmentExceptionResponse(ex.getMessage(), ex.getCause(),badrequest, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<> (appointmentExceptionResponse, badrequest);
    }
    @ExceptionHandler(value =  {AppointmentCustomError.class})
    public ResponseEntity<Object> handleAppointmentCustomExpections(AppointmentCustomError ex)
    {

        HttpStatus status = ex.getStatus();
        AppointmentExceptionResponse appointmentExceptionResponse = new AppointmentExceptionResponse(ex.getMessage(),ex.getCause(), status,ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<> (appointmentExceptionResponse, status);
    }


}
