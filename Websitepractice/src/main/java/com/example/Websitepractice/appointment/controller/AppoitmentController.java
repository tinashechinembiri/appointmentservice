package com.example.Websitepractice.appointment.controller;
import com.example.Websitepractice.appointment.Exceptions.AppointmentCustomError;
import com.example.Websitepractice.appointment.Exceptions.AppointmentExceptions;
import com.example.Websitepractice.appointment.Response.ResponseHandler;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


//https://reflectoring.io/spring-boot-exception-handling/
//https://auth0.com/blog/get-started-with-custom-error-handling-in-spring-boot-java/
//https://howtodoinjava.com/spring-boot2/spring-rest-request-validation/
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
    public ResponseEntity<Object> getsingleappointment(@PathVariable final String appid)
    {

        Appointment appointment =  this.service.getuserbyappid(appid) ;

        return ResponseHandler.createResponse("Appointment found ", HttpStatus.OK, appointment);
    }

    @RequestMapping(value = "/api/addappointment", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> addappointment(@RequestBody final String appointment )
    {

        final Gson gson = new Gson();

        final Appointment app = gson.fromJson(appointment, Appointment.class);

        if(service.getuserbyappid(app.getAppointmentId())==null)
        {
            this.LOG.info("Appointment has been checked that it doesn't exist " + app.getAppointmentId());
             String appointment1 =  service.createappointment(app);
            this.LOG.info("appointment created" + app.getAppointmentId());
            return  ResponseHandler.createResponse("Appointment created", HttpStatus.OK, appointment1);
        }
        throw new AppointmentExceptions("Appointment already exist");
    }
    @RequestMapping(value = "/api/updateappointment", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Object> updateappointment(@RequestBody final String appointment )
    {
        final Gson gson = new Gson();
        final LocalDateTime updateDateTime = java.time.LocalDateTime.now();
        final Appointment app = gson.fromJson(appointment, Appointment.class);

        if(service.getuserbyappid(app.getAppointmentId())!=null)
        {
            service.updateappointment(app);
            return ResponseHandler.createResponse("Appointment Updated", HttpStatus.OK, null);
        }
        throw new AppointmentCustomError(HttpStatus.NOT_FOUND, "Appointment doesn't exist");
    }
    @RequestMapping(value = "/api/cancel/{appointmentId}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public String cancelappointment()
    {
        return null;
    }



}
