package com.evetify.eventify.models;

import jakarta.persistence.*;

public abstract class Attendance {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="UserId", referencedColumnName = "id")
    protected Long userId;
    @ManyToOne
    @JoinColumn(name="EventId", referencedColumnName = "id")
    protected Long eventId;

    public Attendance(Long userId, Long eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}