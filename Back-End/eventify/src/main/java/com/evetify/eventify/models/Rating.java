package com.evetify.eventify.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Integer score;

    public Rating(Integer score) {
        this.score = score;
    }

    public Rating() {

    }
    public Long getId(){
        return id;
    }

    public Integer getRating(){
        return score;
    }

    public void setRating(Integer score){
        this.score = score;
    }
}
