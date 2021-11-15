package com.example.Websitepractice.appointment.pogo;
//import org.bson.*;


import com.example.Websitepractice.appointment.enums.Statusenum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Document(collection="appointment")
public class Appointment {

    @Id
    private String id;
    private String cinemastoreid;
    private String appointmentId;
    private String movieId;
    private Statusenum status;  /// change to enum later
    private Integer quantity;
    private Integer duration;
    private String starttime;
    private LocalDateTime createdDateTime;
    private LocalDateTime  updateDateTime;
    private  MovieDetails moviedetails; // change to class
    private String orderref;
    private String customerid;
    private  Address address;
    private CinemaAddress cinemaAddress;
    private List<Seats> seats;
    private String appointmentDate;
    private String endtime;

    public Appointment() {

    }

    public Appointment(final String id, final String cinemastoreid, final String appointmentId, final String movieId, final Statusenum status, final Integer quantity, final Integer duration, final String starttime, final LocalDateTime createdDateTime, final LocalDateTime updateDateTime, final MovieDetails moviedetails, final String orderref, final String customerid, final Address address, final CinemaAddress cinemaAddress, final List<Seats> seats, final String appointmentDate, final String endtime) {
        this.id = id;
        this.cinemastoreid = cinemastoreid;
        this.appointmentId = appointmentId;
        this.movieId = movieId;
        this.status = status;
        this.quantity = quantity;
        this.duration = duration;
        this.starttime = starttime;
        this.createdDateTime = createdDateTime;
        this.updateDateTime = updateDateTime;
        this.moviedetails = moviedetails;
        this.orderref = orderref;
        this.customerid = customerid;
        this.address = address;
        this.cinemaAddress = cinemaAddress;
        this.seats = seats;
        this.appointmentDate = appointmentDate;
        this.endtime = endtime;
    }

    public String getOrderref() {
        return this.orderref;
    }

    public void setOrderref(final String orderref) {
        this.orderref = orderref;
    }

    public String getAppointmentId() {
        return this.appointmentId;
    }

    public void setAppointmentId(final String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDateTime getCreatedDateTime() {
        return this.createdDateTime;
    }

    public void setCreatedDateTime(final LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return this.updateDateTime;
    }

    public void setUpdateDateTime(final LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public String getCinemastoreid() {
        return this.cinemastoreid;
    }

    public void setCinemastoreid(final String cinemastoreid) {
        this.cinemastoreid = cinemastoreid;
    }

    public String getMovieId() {
        return this.movieId;
    }

    public void setMovieId(final String movieId) {
        this.movieId = movieId;
    }

    public Statusenum getStatus() {
        return this.status;
    }


    public void setStatus(final Statusenum status) {
        this.status = status;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(final Integer duration) {
        this.duration = duration;
    }

    public String getStarttime() {
        return this.starttime;
    }

    public void setStarttime(final String starttime) {
        this.starttime = starttime;
    }


    public MovieDetails getMoviedetails() {
        return this.moviedetails;
    }

    public void setMoviedetails(final MovieDetails moviedetails) {
        this.moviedetails = moviedetails;
    }

    public String getCustomerid() {
        return this.customerid;
    }

    public void setCustomerid(final String customerid) {
        this.customerid = customerid;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public CinemaAddress getCinemaAddress() {
        return this.cinemaAddress;
    }

    public void setCinemaAddress(final CinemaAddress cinemaAddress) {
        this.cinemaAddress = cinemaAddress;
    }



    public List<Seats> getSeats() {
        return this.seats;
    }

    public void setSeats(final List<Seats> seats) {
        this.seats = seats;
    }

    public String getAppointmentDate() {
        return this.appointmentDate;
    }

    public void setAppointmentDate(final String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getEndtime() {
        return this.endtime;
    }

    public void setEndtime(final String endtime) {
        this.endtime = endtime;
    }

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id='" + id + '\'' +
                ", cinemastoreid='" + cinemastoreid + '\'' +
                ", appointmentId='" + appointmentId + '\'' +
                ", movieId='" + movieId + '\'' +
                ", status=" + status +
                ", quantity=" + quantity +
                ", duration=" + duration +
                ", starttime='" + starttime + '\'' +
                ", createdDateTime=" + createdDateTime +
                ", updateDateTime=" + updateDateTime +
                ", moviedetails=" + moviedetails +
                ", orderref='" + orderref + '\'' +
                ", customerid='" + customerid + '\'' +
                ", address=" + address +
                ", cinemaAddress=" + cinemaAddress +
                ", seats=" + seats +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", endtime='" + endtime + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        return super.equals(obj);
    }

}
