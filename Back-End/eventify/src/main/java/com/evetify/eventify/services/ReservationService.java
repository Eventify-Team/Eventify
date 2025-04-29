package com.evetify.eventify.services;

import com.evetify.eventify.models.Reservation;
import com.evetify.eventify.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    ArrayList<Reservation> list = new ArrayList<>();

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

    //Συνδρομή Κυριάκου
    public ArrayList<Reservation> getAllReservationsForEvent(Long eventId){
        return null;
    }

    public Reservation addReservation(Reservation res){
        reservationRepository.save(res);
        return res;
    }

    public void removeReservation(Long id){
        reservationRepository.deleteById(id);
    }
}
