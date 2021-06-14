package com.example.Websitepractice.appointment.pogo;

import com.example.Websitepractice.appointment.enums.Seatenum;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Seats{" +
                "seatid='" + seatid + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", seatType=" + seatType +
                '}';
    }

    public Seats() {
        super();
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (seatid != null) {
            result = 31 * result + seatid.hashCode();
        }
        if (seatType != null) {
            result = 31 * result + seatType.hashCode();
        }
        if(seatNumber!=null)
        {
            result = 31 * result + seatNumber.hashCode();
        }
        return result;

    }

    @Override
    public boolean equals(final Object obj) {
        System.out.println(this.getClass().getName());
        if (obj == this.getClass()) {
            return true;
        }
        if (!(obj instanceof Seats))
            return false;

        Seats guest = (Seats) obj;
        return seatid == guest.seatid
                && (seatNumber == guest.seatNumber
                || (seatNumber != null && seatNumber.equals(guest.getSeatNumber())))
                && (seatType == guest.seatType
                || (seatType != null && seatType .equals(guest.getSeatType())));

    }
}
