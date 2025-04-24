package com.evetify.eventify.models;

import jakarta.persistence.*;

public abstract class Attendance {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="UserId", referencedColumnName = "id")
    private Long userId;
    @ManyToOne
    @JoinColumn(name="EventId", referencedColumnName = "id")
    private Long eventId;

    public Attendance(Long userId, Long eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }
}