package com.example.Websitepractice.appointment.service;

import com.example.Websitepractice.appointment.pogo.Appointment;
import java.time.LocalDateTime;
import java.util.List;


public interface AppointmentServiceres {

     List<Appointment> getallappointment();
     Appointment getuserbyappid(String appId);
     String createappointment (Appointment appointment);
    Appointment updateappointment(Appointment app);
    Appointment Cancelappointment (Appointment app, LocalDateTime updateDateTime);




}
