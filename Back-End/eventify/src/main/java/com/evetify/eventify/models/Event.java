package com.evetify.eventify.models;

public class Event {
    private String id;
    private String name;
    private String description;
    private String location;
    private int capacity;

    public Event(String id, String name, String description, String location, int capacity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
