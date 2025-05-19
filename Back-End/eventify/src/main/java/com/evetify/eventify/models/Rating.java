package com.evetify.eventify.models;

import javax.persistence.*;

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

    public Integer getRatingScore(){
        return score;
    }

    public void setRating(Integer score){
        this.score = score;
    }
}
