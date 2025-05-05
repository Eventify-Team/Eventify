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

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public Long getUserId() {
        return super.getUserId();
    }
}
