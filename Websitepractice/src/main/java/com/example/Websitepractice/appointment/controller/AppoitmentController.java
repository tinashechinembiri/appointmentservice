package com.example.Websitepractice.appointment.controller;
import com.example.Websitepractice.appointment.pogo.Appointment;
import com.example.Websitepractice.appointment.service.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;


//@RequestMapping(value = "/")
@RestController
public class AppoitmentController {
  //  private final Logger LOG = (Logger) LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;
    public AppoitmentController (UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @RequestMapping(value ="/api", method = RequestMethod.GET)
    public  List<Appointment> getallusers()
    {
        //LOG.info("Getting all users.");
        return userRepository.findAll();
    }
    @RequestMapping(value ="/api/getallappointment", method = RequestMethod.GET)
    public  List<Appointment> getallappointment()
    {
        return  null;
    }
    @RequestMapping(value ="/api/getsingleappointment/{appointmentid}", method = RequestMethod.GET)
    public String getsingleappointment(String app)
    {
        return  null;
    }

}
