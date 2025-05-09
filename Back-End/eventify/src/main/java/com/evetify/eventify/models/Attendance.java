package com.evetify.eventify.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="UserId", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name="EventId", referencedColumnName = "id")
    private Event event;

    @OneToOne
    private Rating rating;

    @OneToOne
    private Reservation reservation;
    public Attendance(User user, Event event) {
        this.user= user;
        this.event = event;
        this.reservation = new Reservation(LocalDate.now());
        this.rating = new Rating(null);
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

    public Integer getRating(){return rating.getRating();}
    public void setRating(Integer score){rating.setRating(score);}
    public boolean checkReservation(){return reservation.isValid();}
    public void cancelReservation(){reservation.setValid(false);}


}