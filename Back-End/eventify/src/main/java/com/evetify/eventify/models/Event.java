package com.evetify.eventify.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int duration;
    private String location;
    private int capacity;
    private Date date;
    private double fee;
    @OneToMany(mappedBy = "eventId")
    private ArrayList<Attendance> attendances = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name="AdminID", referencedColumnName = "id")
    private Long adminId;

    public Event(String name, String description, int duration, String location, int capacity,Date aDate, double fee) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.location = location;
        this.capacity = capacity;
        this.date = aDate;
        this.fee = fee;
    }

    public Event(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public ArrayList<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(ArrayList<Attendance> attendances) {
        this.attendances = attendances;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public ArrayList<Reservation> getAllReservationsForEvent(){
        ArrayList<Reservation> res= new ArrayList<>();
        for(Attendance att : attendances){
            if(att instanceof Reservation)
                res.add((Reservation) att);

        }
        return res;
    }

    public ArrayList<Rating> getAllRatingsForEvent(){
        ArrayList<Rating> rat= new ArrayList<>();
        for(Attendance att : attendances){
            if(att instanceof Rating)
                rat.add((Rating) att);


        }
        return rat;
    }

    public void addRatingForEvent(Rating rating){
        attendances.add(rating);
    }

    public Boolean HasReservation(Long userId){
        for(Attendance att: attendances){
            if(att instanceof Reservation){
                if(att.getUserId().equals(userId)){
                    return true;
                }
            }
        }
        return false;
    }
}
