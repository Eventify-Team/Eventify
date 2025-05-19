package com.evetify.eventify.models;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="UserId")
    private User user;
    @ManyToOne
    @JoinColumn(name="EventId", referencedColumnName = "id")
    @JsonIgnore
    private Event event;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Rating rating;

    @OneToOne
    private Reservation reservation;
    public Attendance(User user, Event event) {
        this.user= user;
        this.event = event;
        this.reservation = new Reservation(LocalDate.now());
        this.rating = new Rating(0);
    }
    public Attendance() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return user.getId();
    }
    public String getUsername(){return user.getUsername();}


    public Long getEventId() {
        return event.getId();
    }

    public Reservation getReservation() {
        return reservation;
    }

    public Rating getRat(){
        return rating;
    }


    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Integer getRating(){return rating.getRatingScore();}
    public void setRating(Integer score){rating.setRating(score);}


}