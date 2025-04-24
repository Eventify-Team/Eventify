package com.evetify.eventify.models;

import jakarta.persistence.Entity;

@Entity
public class Rating extends Attendance{

    private int score;

    public Rating(Long userId, Long eventId, int score) {
        super( userId, eventId);
        this.score = score;
    }
}
