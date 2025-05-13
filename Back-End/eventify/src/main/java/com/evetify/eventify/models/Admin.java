package com.evetify.eventify.models;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import java.util.ArrayList;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private String email;
    @OneToMany(mappedBy = "adminId")
    private ArrayList<Event> events = new ArrayList<>();

    public Admin(String name, String password, String email, ArrayList<Event> events) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.events = events;
    }

    public Admin(){

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }


    public void addEvent(Event event){ events.add(event);}
}
