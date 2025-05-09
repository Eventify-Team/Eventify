package com.evetify.eventify.models;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    @OneToMany(mappedBy = "userId")
    private ArrayList<Attendance> attendances = new ArrayList<>();

    public User(String name, String surname, String username, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(ArrayList<Attendance> attendances) {
        this.attendances = attendances;
    }

    public void addAttendance(Attendance a){
        attendances.add(a);
    }
    public void addRatingForEvent(Long eventId, Integer score){
        for(Attendance att: attendances){
            if(att.getEventId().equals(eventId))
                att.setRating(score);
        }
    }
    public void cancelReservation(Long eventId){
        for(Attendance att: attendances){
            if(att.getEventId().equals(eventId))
                attendances.remove(att);
        }
    }
}
