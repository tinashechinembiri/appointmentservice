package com.example.Websitepractice.appointment.service;

import com.example.Websitepractice.appointment.enums.Statusenum;
import com.example.Websitepractice.appointment.pogo.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Repository
public class AppointmentServiceimpl implements  AppointmentServiceres{

    @Autowired
    private MongoTemplate mongoTemplate;

    public Appointment createappointment (Appointment appointment, LocalDateTime createdDateTime, LocalDateTime updateDateTime )
    {
        Appointment appointment1 = mapappointment(appointment, createdDateTime, updateDateTime);
        mongoTemplate.save(appointment1);

        return  appointment1;

    }
    public Appointment updateappointment()
    {
        return  null;
    }

    public  Appointment Cancelappointment ()
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
    private Appointment mapappointment(Appointment appointment, LocalDateTime createdDateTime, LocalDateTime updateDateTime)
    {
        Appointment createappointment = new Appointment();

        createappointment.setCreatedDateTime(createdDateTime);
        createappointment.setUpdateDateTime(updateDateTime);

       //reateappointment.setId(new ObjectId().toHexString());
        createappointment.setAddress(mapaddress(appointment));
        createappointment.setMoviedetails(mapmovies(appointment));
        createappointment.setSeats(mapseats(appointment));
        createappointment.setCustomerid(appointment.getCustomerid());
        createappointment.setAppointmentId(appointment.getAppointmentId());
        createappointment.setCinemaAddress(mapcinemaaddress(appointment));
        createappointment.setCinemastoreid(appointment.getCinemastoreid());
        createappointment.setMovieId(appointment.getMovieId());
        createappointment.setOrderref(appointment.getOrderref());
        createappointment.setStatus(Statusenum.RESERVED);
        createappointment.setStarttime(appointment.getStarttime());
        createappointment.setDuration(appointment.getDuration());
        createappointment.setEndtime(appointment.getEndtime());

        return  createappointment;
    }
    private Address mapaddress(Appointment address)
    {

        Address addaddress = new Address();
        addaddress.setCountry(address.getAddress().getCountry());
        addaddress.setStreetName(address.getAddress().getStreetName());
        addaddress.setMobileNo(address.getAddress().getMobileNo());
        addaddress.setPhoneNo(address.getAddress().getPhoneNo());
        addaddress.setPostcode(address.getAddress().getPostcode());


        return addaddress;
    }
    private MovieDetails mapmovies(Appointment movieDetails)
    {
        MovieDetails movie = new MovieDetails();
        movie.setMovieid(movieDetails.getMoviedetails().getMovieid());
        movie.setMovieyear(movieDetails.getMoviedetails().getMovieyear());
        movie.setMoviename(movieDetails.getMoviedetails().getMoviename());
        movie.setDirectors(movieDetails.getMoviedetails().getDirectors());
        movie.setStars(movieDetails.getMoviedetails().getStars());
        movie.setWriters(movieDetails.getMoviedetails().getWriters());
        return movie;
    }
    private List<Seats> mapseats(Appointment seats)
    {
        List<Seats> seats1 = new ArrayList<>();
        seats1.addAll(seats.getSeats());
        return  seats1;
    }
    private CinemaAddress mapcinemaaddress (Appointment appointment)
    {
        CinemaAddress cinemaAddress = new CinemaAddress();
        cinemaAddress.setCounty(appointment.getCinemaAddress().getCounty());
        cinemaAddress.setMobile(appointment.getCinemaAddress().getMobile());
        cinemaAddress.setCountry(appointment.getCinemaAddress().getCountry());
        cinemaAddress.setPhone(appointment.getCinemaAddress().getPhone());
        cinemaAddress.setName(appointment.getCinemaAddress().getName());
        cinemaAddress.setPostCode(appointment.getCinemaAddress().getPostCode());
        cinemaAddress.setStreetName(appointment.getCinemaAddress().getStreetName());
        cinemaAddress.setTown(appointment.getCinemaAddress().getTown());

        return cinemaAddress;
    }
}
