package com.evetify.eventify.controllers;

import com.evetify.eventify.models.Event;
import com.evetify.eventify.models.Reservation;
import com.evetify.eventify.services.AttendanceService;
import com.evetify.eventify.services.EventService;
import com.evetify.eventify.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")

public class ReservationController {

    @Autowired ReservationService reservationService;
    @Autowired AttendanceService attendanceService;


    @Autowired
    EventService eventService;

    @GetMapping("/getReservation")
    public Reservation getReservation(@RequestParam Long reservationId){
        return reservationService.getReservation(reservationId);
    }

    @GetMapping("/getAllReservations")
    public List<Reservation> getAllReservations(){
        List<Reservation> reservationList = reservationService.getAllReservations();
        return reservationList;
    }

    @PostMapping("/addReservation")
    public Reservation addReservation(@RequestBody Reservation res){
        Reservation reservation = reservationService.addReservation(res);
        return reservation;
    }

    @DeleteMapping("/removeReservation")
    public void removeReservation(@RequestParam Long reservationId){
        reservationService.removeReservation(reservationId);
    }

    @GetMapping("checkReservation")
    public boolean checkReservation(@RequestParam Long attendanceId){
        return attendanceService.checkReservation(attendanceId);
    }
}

