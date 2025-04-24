package com.evetify.eventify.models;

import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class Reservation extends Attendance{

    private boolean valid;
    private Date date;

    public Reservation(Long userId, Long eventId, boolean valid, Date date) {
        super(userId, eventId);
        this.valid = valid;
        this.date = date;
    }

}
