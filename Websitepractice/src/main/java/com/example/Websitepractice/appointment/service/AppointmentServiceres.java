package com.example.Websitepractice.appointment.service;

import com.example.Websitepractice.appointment.pogo.Appointment;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


public interface AppointmentServiceres {

    public List<Appointment> getallappointment();
    public Appointment getuserbyappid(String appId);
    public Appointment createappointment (Appointment appointment, LocalDateTime createdDateTime, LocalDateTime updateDateTime );


}
