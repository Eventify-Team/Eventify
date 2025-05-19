package com.evetify.eventify.services;

import com.evetify.eventify.models.*;
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
import java.util.List;
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



    public Reservation getReservation(Long reservationId){

        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);

        if(optionalReservation.isPresent()){
            return optionalReservation.get();
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Not Found");
        }
    }
    public List<Reservation> getAllReservations(){
        return (List<Reservation>) reservationRepository.findAll();
    }


    public Reservation addReservation(Reservation res){
        reservationRepository.save(res);
        return res;
    }

    public List<Reservation> getAllReservationsForEvent(Long eventId){
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if(!optionalEvent.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found");
        Event event = optionalEvent.get();
        List<Attendance> attendances = event.getAttendances();
        List<Reservation> reservations = new ArrayList<>();
        for(Attendance att: attendances){
            reservations.add(att.getReservation());
        }
        return reservations;
    }

    public void removeReservation(Long reservationId){
        reservationRepository.deleteById(reservationId);
    }

//    public List<User> getAllUsersForEvent(Long eventId){
//        List<User> users = new ArrayList<>();
//        Optional<Event> eventopt = eventRepository.findById(eventId);
//        if(eventopt.isPresent()){
//            Event event = eventopt.get();
//            List<Attendance> attendances = event.getAttendances();
//            for(Attendance att : attendances){
//                Long usrId = att.getUserId();
//                Optional<User> user = userRepository.findById(usrId);
//                if(user.isPresent())
//                    users.add(user.get());
//                else
//                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found!");
//            }
//        }else{
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found!");
//        }
//        return users;
//    }
}
