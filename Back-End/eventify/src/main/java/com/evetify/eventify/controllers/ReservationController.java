package com.evetify.eventify.controllers;

import com.evetify.eventify.models.Reservation;
import com.evetify.eventify.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservation")

public class ReservationController {

    @Autowired ReservationService reservationService;

    @GetMapping("/getReservation")
    public Reservation getReservation(@RequestParam Long id){
        return reservationService.getReservation(id);
    }

    @GetMapping("/getAllReservations")
    public ArrayList<Reservation> getAllReservations(){
        ArrayList<Reservation> list = reservationService.getAllReservations();
        return list;
    }

    @GetMapping("/getAllReservationsForEvent")
    public ArrayList<Reservation> getAllReservationsForEvent(@RequestParam Long eventId){
        ArrayList<Reservation> list = reservationService.getAllReservationsForEvent(eventId);
        return list;
    }

    @PostMapping
    public Reservation addReservation(@RequestBody Reservation reservation){
        Reservation res = reservationService.addReservation(reservation);
        return res;
    }

    @DeleteMapping()
    public void removeResevation(@RequestParam Long id){
        reservationService.removeReservation(id);
    }


}

