package com.evetify.eventify.models;

import jakarta.persistence.*;

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

}
