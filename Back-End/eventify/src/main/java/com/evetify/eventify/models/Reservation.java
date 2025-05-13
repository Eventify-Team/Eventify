package com.evetify.eventify.models;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private boolean valid;
    private LocalDate date;

    public Reservation(LocalDate date) {
        this.valid = true;
        this.date = date;
    }
    public Reservation() {

    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


}
