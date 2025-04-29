package com.evetify.eventify.services;

import com.evetify.eventify.models.Rating;
import com.evetify.eventify.models.Reservation;
import com.evetify.eventify.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;


    public Rating getRating(Long id) {
        Optional<Rating> optionalRating = ratingRepository.findById(id);

        if(optionalRating.isPresent()){
            return optionalRating.get();
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Not Found");
        }

    }

    //Χρειαζεται service Κυριακου
    public ArrayList<Rating> getRatingsForEvent(Long eventId) {
        return null;
    }


    public Rating addRating(Rating rating) {
        ratingRepository.save(rating);
        return rating;
    }

    public void removeRating(Long id){
       ratingRepository.deleteById(id);
    }
}
