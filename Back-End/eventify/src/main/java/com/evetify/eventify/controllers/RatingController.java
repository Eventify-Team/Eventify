package com.evetify.eventify.controllers;

import com.evetify.eventify.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.evetify.eventify.models.Rating;

import java.util.ArrayList;

@Controller
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    RatingService ratingService;

    @GetMapping("/getRating")
    public Rating getRating(@RequestParam Long id ){
        return ratingService.getRating(id);
    }

    @GetMapping("/getAllRatingsForEvent")
    public ArrayList<Rating> getRatingsForEvent(@RequestParam Long eventId){
        return ratingService.getRatingsForEvent(eventId);
    }

    @PostMapping
    public Rating addRating(@RequestBody Rating rating){
        Rating rat = ratingService.addRating(rating);
        return rat;
    }

    @DeleteMapping
    public void deleteRating(@RequestParam Long id){
        ratingService.removeRating(id);
    }



}
