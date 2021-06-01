package com.example.Websitepractice.appointment.service;

import com.example.Websitepractice.appointment.pogo.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class AppointmentServiceimpl implements  AppointmentServiceres{

    @Autowired
    private MongoTemplate mongoTemplate;
    public String createappointment ()
    {


        return  null;

    }
    public String updateappointment()
    {
        return  null;
    }
    public  String Cancelappointment ()
    {
        return  null;

    }

    public List<Appointment> getallappointment()
    {

        return mongoTemplate.findAll(Appointment.class) ;
    }

    public Appointment getuserbyappid(String appId)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("appointmentid").is(appId));
        return  mongoTemplate.findOne(query,Appointment.class);
    }
}
