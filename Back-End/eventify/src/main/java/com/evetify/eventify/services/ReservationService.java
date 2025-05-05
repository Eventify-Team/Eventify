package com.evetify.eventify.services;

import com.evetify.eventify.models.Event;
import com.evetify.eventify.models.Rating;
import com.evetify.eventify.models.Reservation;
import com.evetify.eventify.models.User;
import com.evetify.eventify.repositories.EventRepository;
import com.evetify.eventify.repositories.RatingRepository;
import com.evetify.eventify.repositories.ReservationRepository;
import com.evetify.eventify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    RatingRepository ratingRepository;

    ArrayList<Reservation> list = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();

    public Reservation getReservation(Long id){

        Optional<Reservation> optionalReservation = reservationRepository.findById(id);

        if(optionalReservation.isPresent()){
            return optionalReservation.get();
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Not Found");
        }
    }
    public ArrayList<Reservation> getAllReservations(){
        return (ArrayList<Reservation>) reservationRepository.findAll();
    }




    public Reservation addReservation(Reservation res){
        reservationRepository.save(res);
        return res;
    }

    public void removeReservation(Long id){
        reservationRepository.deleteById(id);
    }

    public ArrayList<User> getAllUsersForEvent(Long eventId){
        Optional<Event> eventopt = eventRepository.findById(eventId);
        if(eventopt.isPresent()){
            Event event = eventopt.get();
            ArrayList<Reservation> reservations = event.getAllReservationsForEvent();
            for(Reservation res : reservations){
                Long usrId = res.getUserId();
                Optional<User> user = userRepository.findById(usrId);
                if(user.isPresent())
                    users.add(user.get());
                else
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found!");

            }
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not find!");
        }



        return users;
    }

    public void addRatingForEvent(Rating rating){
        Event event;
        User user;
        Long userId = rating.getUserId();
        Long eventId = rating.getEventId();
        Optional<Event> optevent = eventRepository.findById(eventId);

        if(optevent.isPresent()) {
            event = optevent.get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found!");
        }

        if(event.HasReservation(userId)){
            ratingRepository.save(rating);
            event.addRatingForEvent(rating);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found!");
        }
    }
}
