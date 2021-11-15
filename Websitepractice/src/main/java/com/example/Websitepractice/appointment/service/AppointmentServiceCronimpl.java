package com.example.Websitepractice.appointment.service;

import com.example.Websitepractice.appointment.enums.Statusenum;
import com.example.Websitepractice.appointment.pogo.Appointment;

import java.util.List;

public class AppointmentServiceCronimpl extends AppointmentServiceimpl {
    public Appointment bookedappointment()
    {
        List<Appointment> appointments = getallappointment();
        appointments.stream().filter(s -> s.getStatus().equals(Statusenum.RESERVED)).map(
                s ->
                {
                    Appointment update = getuserbyappid(s.getAppointmentId());
                    update.setStatus(Statusenum.BOOKED);
                    return s;
                }
        );
        return  null;
    }
}
