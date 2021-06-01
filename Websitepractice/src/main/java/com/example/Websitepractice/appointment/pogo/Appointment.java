package com.example.Websitepractice.appointment.pogo;
//import org.bson.*;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection="appointment")
public class Appointment {

    @Id
    private String Id;
    private String cinemastoreid;
    private String appointmentId;
    private String seatid;
    private String movieId;
    private String status;  /// change to enum later
    private Integer quantity;
    private Integer duration;
    private String starttime;
    private LocalDateTime createdDateTime;
    private LocalDateTime  updateDateTime;
    private String storeaddress; // change to a class
    private  String moviedetails; // change to class
    private String orderref;


    public String getId() {
        return this.Id;
    }

    public void setId(final String id) {
        this.Id = id;
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

    public String getSeatid() {
        return this.seatid;
    }

    public void setSeatid(final String seatid) {
        this.seatid = seatid;
    }

    public String getMovieId() {
        return this.movieId;
    }

    public void setMovieId(final String movieId) {
        this.movieId = movieId;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
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


    public String getStoreaddress() {
        return this.storeaddress;
    }

    public void setStoreaddress(final String storeaddress) {
        this.storeaddress = storeaddress;
    }

    public String getMoviedetails() {
        return this.moviedetails;
    }

    public void setMoviedetails(final String moviedetails) {
        this.moviedetails = moviedetails;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "Id='" + Id + '\'' +
                ", cinemastoreid='" + cinemastoreid + '\'' +
                ", seatid='" + seatid + '\'' +
                ", movieId='" + movieId + '\'' +
                ", status='" + status + '\'' +
                ", quantity=" + quantity +
                ", duration=" + duration +
                ", starttime='" + starttime + '\'' +
                ", createdDateTime=" + createdDateTime +
                ", updateDateTime=" + updateDateTime +
                ", storeaddress='" + storeaddress + '\'' +
                ", moviedetails='" + moviedetails + '\'' +
                '}';
    }
}
