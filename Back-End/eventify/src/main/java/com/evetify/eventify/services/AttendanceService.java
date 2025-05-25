package com.evetify.eventify.services;

import com.evetify.eventify.models.*;
import com.evetify.eventify.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    AttendanceRepository attendanceRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    TicketPdfService ticketPdfService;


    public void removeAttendance(Long attendanceId) {
        Optional<Attendance> att = attendanceRepository.findById(attendanceId);
        if(!att.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        Attendance attendance = att.get();
        attendanceRepository.deleteById(attendanceId);
        Long reservationId = attendance.getReservation().getId();
        Long ratingId = attendance.getRat().getId();
        ratingRepository.deleteById(ratingId);
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (!optionalReservation.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation Not Found");
        Reservation reservation = optionalReservation.get();
        reservation.setValid(false);
        reservationRepository.save(reservation);

    }

    public void addAttendance(Long userId, Long eventId) throws IOException {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if(!optionalEvent.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found");
        Event event = optionalEvent.get();
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
        User user = optionalUser.get();
        Attendance attendance = new Attendance(user, event);
        Reservation reservation = attendance.getReservation();
        reservationRepository.save(reservation);
        attendance.setReservation(reservation);
        attendanceRepository.save(attendance);
        File file = ticketPdfService.generateTicket(user.getName(), user.getSurname(), event.getName(), event.getLocation(), event.getDescription(), event.getDate(), event.getTime() ,event.getFee(), attendance.getId());

    }

    public List<Attendance> getAttendancesForUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
        User user = optionalUser.get();
        List<Attendance> attendances = user.getAttendances();
        return attendances;
    }

    public boolean checkReservation(Long attendanceId){
        Optional<Attendance> att = attendanceRepository.findById(attendanceId);
        if(!att.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation is cancelled");
        Attendance attendance = att.get();
        return attendance.getReservation().isValid();
    }

}
