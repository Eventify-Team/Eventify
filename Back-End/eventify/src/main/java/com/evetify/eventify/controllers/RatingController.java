package com.evetify.eventify.controllers;

import com.evetify.eventify.models.Event;
import com.evetify.eventify.services.EventService;
import com.evetify.eventify.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.evetify.eventify.models.Rating;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Handler;

@Controller
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    RatingService ratingService;

    @Autowired
    EventService eventService;

    @GetMapping("/getRating")
    public Rating getRating(@RequestParam Long ratingId){
        return ratingService.getRating(ratingId);
    }

    @PostMapping("/addRating")
    public Rating addRating(@RequestBody Rating rat){
        Rating rating = ratingService.addRating(rat);
        return rating;
    }

    @DeleteMapping("/deleteRating")
    public void deleteRating(@RequestParam Long ratingid){
        ratingService.removeRating(ratingid);
    }
}
