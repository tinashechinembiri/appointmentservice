package com.example.Websitepractice.appointment.service;

import com.example.Websitepractice.appointment.enums.Statusenum;
import com.example.Websitepractice.appointment.pogo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Service("AppointmentServiceres")
public class AppointmentServiceimpl implements  AppointmentServiceres{

    //@Autowired

    @Autowired
   // @Qualifier("primaryMongoTemplate")
    private MongoTemplate  mongoTemplate;

    private Appointment appointment1 ;
    public AppointmentServiceimpl ()
    {

    }
    public AppointmentServiceimpl(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    @Override
    public Appointment createappointment (Appointment appointment )
    {
        appointment1 = mapappointment(appointment);
        mongoTemplate.save(appointment1);
        return  appointment1;
    }
    @Override
    public Appointment updateappointment(Appointment app, LocalDateTime updateDateTime)
    {
        Appointment updateappintment = mapupdateappointment(app, updateDateTime);

        mongoTemplate.save(updateappintment);
        return  updateappintment;
    }

    private Appointment mapupdateappointment(Appointment app, LocalDateTime updateDateTime)
    {
        Appointment update =  getuserbyappid(app.getAppointmentId());

        if (app.getAppointmentDate()!=null && app.getStarttime()!=null)
        {
            update.setUpdateDateTime(updateDateTime);
            update.setAppointmentDate(checkdate(app.getAppointmentDate()));
            update.setStarttime(app.getStarttime());
            update.setCinemaAddress(mapcinemaaddress(app));
            update.setAddress( mapaddress(app));
            update.setSeats( updateseats(app, update));
        }
     return  update;
    }
    @Override
    public  Appointment Cancelappointment (Appointment app, LocalDateTime updateDateTime)
    {
        Appointment cancel =  getuserbyappid( app.getAppointmentId());
        if (Statusenum.BOOKED.equals(cancel.getStatus()) || Statusenum.RESERVED.equals(cancel.getStatus())) {
            cancel.setUpdateDateTime(updateDateTime);
            cancel.setStatus(Statusenum.CANCELLED);
            mongoTemplate.save(cancel);
        }
        return  cancel;
    }

    @Override
    public List<Appointment> getallappointment()
    {

        return mongoTemplate.findAll(Appointment.class) ;
    }
    @Override
    public Appointment getuserbyappid(String appId)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("appointmentId").is(appId));
        return  mongoTemplate.findOne(query,Appointment.class);
    }
    private Appointment mapappointment(Appointment appointment)
    {
        LocalDateTime createdDateTime = java.time.LocalDateTime.now();
        LocalDateTime updateDateTime = java.time.LocalDateTime.now();
        Appointment createappointment = new Appointment();

        createappointment.setCreatedDateTime(createdDateTime);
        createappointment.setUpdateDateTime(updateDateTime);

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
        createappointment.setAppointmentDate(checkdate(appointment.getAppointmentDate()));
        createappointment.setStarttime(appointment.getStarttime());
        createappointment.setDuration(appointment.getDuration());
        createappointment.setEndtime(appointment.getEndtime());

        return  createappointment;
    }
    private Address mapaddress(Appointment address)
    {

        Address addaddress = new Address();
        if (address.getAddress()!=null) {
            addaddress.setCountry(address.getAddress().getCountry());
            addaddress.setStreetName(address.getAddress().getStreetName());
            addaddress.setMobileNo(address.getAddress().getMobileNo());
            addaddress.setPhoneNo(address.getAddress().getPhoneNo());
            addaddress.setPostcode(address.getAddress().getPostcode());
        }
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
    private String checkdate(String bookingtime)  {
        LocalDateTime date = LocalDateTime.parse(bookingtime,
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH));

        if (date.toLocalDate().isBefore(java.time.LocalDate.now()))
        {
            try {
                throw new Exception("selected date is wrong ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  date.toString();
    }
    private List<Seats> mapseats(Appointment seats)
    {
        List<Seats> seats1 = new ArrayList<>();
        seats1.addAll(seats.getSeats());
        return  seats1;
    }
    private List<Seats> updateseats(Appointment app, Appointment update )
    {
        List<Seats> seats1 = new ArrayList<>();

        if (app.getSeats()!= null)
        {
            Set<String> exclusions = update.getSeats().stream()
                    .map(Seats::getSeatid)
                    .collect(Collectors.toSet());

            List<Seats> item = app.getSeats().stream()
                 .filter(sortlist -> !exclusions.contains(sortlist.getSeatid()))
                    .collect(Collectors.toList());
            for (Seats i : item)
            {
                seats1.add(i);
            }
        }
        return  seats1;
    }
    private CinemaAddress mapcinemaaddress (Appointment appointment)
    {
        CinemaAddress cinemaAddress = new CinemaAddress();
        if (appointment.getCinemaAddress()!=null) {
            cinemaAddress.setCounty(appointment.getCinemaAddress().getCounty());
            cinemaAddress.setMobile(appointment.getCinemaAddress().getMobile());
            cinemaAddress.setCountry(appointment.getCinemaAddress().getCountry());
            cinemaAddress.setPhone(appointment.getCinemaAddress().getPhone());
            cinemaAddress.setName(appointment.getCinemaAddress().getName());
            cinemaAddress.setPostCode(appointment.getCinemaAddress().getPostCode());
            cinemaAddress.setStreetName(appointment.getCinemaAddress().getStreetName());
            cinemaAddress.setTown(appointment.getCinemaAddress().getTown());
        }
        return cinemaAddress;
    }

    @Override
    public String toString() {
        return "AppointmentServiceimpl{" +
                "mongoTemplate=" + mongoTemplate +
                '}';
    }
}
