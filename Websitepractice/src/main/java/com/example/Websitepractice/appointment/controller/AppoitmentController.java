package com.example.Websitepractice.appointment.controller;
import com.example.Websitepractice.appointment.pogo.Appointment;
import com.example.Websitepractice.appointment.service.AppointmentServiceres;
import com.example.Websitepractice.appointment.service.UserRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.Gson;
import org.bson.json.JsonObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;



//@RequestMapping(value = "/")
@RestController
public class AppoitmentController {
    private final Logger LOG = LoggerFactory.getLogger(AppoitmentController.class.getName());

    private final UserRepository userRepository;
    private final AppointmentServiceres service;
    public AppoitmentController(UserRepository userRepository, final AppointmentServiceres service)
    {
        this.userRepository = userRepository;
        this.service = service;
    }

    @RequestMapping(value ="/api", method = RequestMethod.GET)
    public  List<Appointment> getallusers()
    {
        LOG.info("Getting all users.");
        return userRepository.findAll();
    }
    @RequestMapping(value ="/api/getallappointment", method = RequestMethod.GET)
    public  List<Appointment> getallappointment()
    {
        return  service.getallappointment();
    }
    @RequestMapping(value ="/api/getallappointment/{cinemaid}", method = RequestMethod.GET)
    public  List<Appointment> getallapppointmentbycinema()
    {
        return  null;
    }
    @RequestMapping(value ="/api/getsingleappointment/{appid}", method = RequestMethod.GET)
    public String getsingleappointment(@PathVariable String appid)
    {
        return  null;
    }

    @RequestMapping(value ="/api/addappointment", method = RequestMethod.POST, produces = "application/json", consumes = {"application/json"})
    public String addappointment(@RequestBody String appointment )
    {
        Gson gson = new Gson();
        LocalDateTime createdDateTime = java.time.LocalDateTime.now();
        LocalDateTime updateDateTime = java.time.LocalDateTime.now();

        Appointment app = gson.fromJson(appointment, Appointment.class);

        if(service.getuserbyappid(app.getAppointmentId())==null)
        {
            Appointment appointment1 =  service.createappointment(app,createdDateTime,updateDateTime );

            LOG.info("appointment created");
            return  appointment1.toString();

        }

        LOG.info("appointment can be added because already exist");
        return "appointment exist";
    }
    @RequestMapping(value ="/api/updateappointment", method = RequestMethod.PUT, produces = "application/json", consumes = {"application/json"})
    public String updateappointment(@RequestBody String appointment )
    {
        Gson gson = new Gson();
        LocalDateTime updateDateTime = java.time.LocalDateTime.now();
        Appointment app = gson.fromJson(appointment, Appointment.class);

        if(service.getuserbyappid(app.getAppointmentId())!=null)
        {
            Appointment appointment1 = service.updateappointment(app, updateDateTime);
            return  appointment1.toString();
        }
        return "couldn't update ";
    }
    @RequestMapping(value ="/api/cancel/{appointmentId}", method = RequestMethod.PUT, produces = "application/json", consumes = {"application/json"})
    public String cancelappointment()
    {
        return null;
    }



}
