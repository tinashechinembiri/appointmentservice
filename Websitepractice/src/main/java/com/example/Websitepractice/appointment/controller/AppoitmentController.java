package com.example.Websitepractice.appointment.controller;
import com.example.Websitepractice.appointment.pogo.Appointment;
import com.example.Websitepractice.appointment.service.AppointmentServiceres;
import com.example.Websitepractice.appointment.service.UserRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.Gson;
import org.bson.json.JsonObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;



//@RequestMapping(value = "/")
@RestController
public class AppoitmentController {
    private final Logger LOG = LoggerFactory.getLogger(AppoitmentController.class.getName());


    @Autowired
    private  AppointmentServiceres service;

    public AppoitmentController(  AppointmentServiceres service)
    {

        this.service = service;
    }

    @RequestMapping(value ="/api", method = RequestMethod.GET)
    public  List<Appointment> getallusers()
    {
        this.LOG.info("Getting all users.");
        return this.service.getallappointment();
    }
    @RequestMapping(value ="/api/getallappointment", method = RequestMethod.GET)
    public  List<Appointment> getallappointment()
    {
        return this.service.getallappointment();
    }
    @RequestMapping(value ="/api/getallappointment/{cinemaid}", method = RequestMethod.GET)
    public  List<Appointment> getallapppointmentbycinema()
    {
        return  null;
    }
    @RequestMapping(value ="/api/getsingleappointment/{appid}", method = RequestMethod.GET)
    public Appointment getsingleappointment(@PathVariable final String appid)
    {

        return this.service.getuserbyappid(appid) ;
    }

    @RequestMapping(value = "/api/addappointment", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public String addappointment(@RequestBody final String appointment )
    {

        final Gson gson = new Gson();

        final Appointment app = gson.fromJson(appointment, Appointment.class);

        if(service.getuserbyappid(app.getAppointmentId())==null)
        {
           Appointment appointment1 =  service.createappointment(app);

            this.LOG.info("appointment created" + app.getAppointmentId());
            return  "appointment created";

        }

        this.LOG.info("appointment can be added because already exist"+ app.getAppointmentId());
        return "appointment exist";
    }
    @RequestMapping(value = "/api/updateappointment", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String updateappointment(@RequestBody final String appointment )
    {
        final Gson gson = new Gson();
        final LocalDateTime updateDateTime = java.time.LocalDateTime.now();
        final Appointment app = gson.fromJson(appointment, Appointment.class);

        if(service.getuserbyappid(app.getAppointmentId())!=null)
        {
            service.updateappointment(app, updateDateTime);
            return  "appointment Updated";
        }
        return "couldn't update ";
    }
    @RequestMapping(value = "/api/cancel/{appointmentId}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public String cancelappointment()
    {
        return null;
    }



}
