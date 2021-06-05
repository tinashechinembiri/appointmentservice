package com.example.Websitepractice.appointment.pogo;

import com.example.Websitepractice.appointment.enums.Seatenum;

public class Seats {
    private String seatid;
    private String seatNumber;
    private Seatenum seatType;

    public String getSeatid() {
        return this.seatid;
    }

    public void setSeatid(final String seatid) {
        this.seatid = seatid;
    }

    public String getSeatNumber() {
        return this.seatNumber;
    }

    public void setSeatNumber(final String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Seatenum getSeatType() {
        return this.seatType;
    }

    public void setSeatType(final Seatenum seatType) {
        this.seatType = seatType;
    }
}
