package com.evetify.eventify.controllers;

import com.evetify.eventify.models.Event;
import com.evetify.eventify.models.Reservation;
import com.evetify.eventify.services.AttendanceService;
import com.evetify.eventify.services.EventService;
import com.evetify.eventify.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservation")

public class ReservationController {

    @Autowired ReservationService reservationService;
    @Autowired AttendanceService attendanceService;


    @Autowired
    EventService eventService;

    @GetMapping("/getReservation")
    public Reservation getReservation(@RequestParam Long id){
        return reservationService.getReservation(id);
    }

    @GetMapping("/getAllReservations")
    public List<Reservation> getAllReservations(){
        List<Reservation> list = reservationService.getAllReservations();
        return list;
    }

    @GetMapping("/getAllReservationsForEvent")
    public List<Reservation> getAllReservationsForEvent(@RequestParam Long eventId){
        Event event = eventService.getEvent(eventId);
        List<Reservation> reservations = reservationService.getAllReservationsForEvent(eventId);
        return reservations;
    }

    @PostMapping
    public Reservation addReservation(@RequestBody Reservation reservation){
        Reservation res = reservationService.addReservation(reservation);
        return res;
    }

    @DeleteMapping
    public void removeResevation(@RequestParam Long id){
        reservationService.removeReservation(id);
    }

    @GetMapping("checkReservation")
    public boolean checkReservation(@RequestParam Long attendanceId){
        return attendanceService.checkReservation(attendanceId);
    }
}

