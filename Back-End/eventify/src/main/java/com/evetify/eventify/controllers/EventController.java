package com.evetify.eventify.controllers;

import com.evetify.eventify.models.Attendance;
import com.evetify.eventify.models.Event;
import com.evetify.eventify.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    EventService eventService;

    @GetMapping("/getallevents")
    public List<Event> getAllEvents(){
        List<Event> events = eventService.getAllEvents();
        return events;
    }

    @GetMapping("/getevent")
    public Event getEvent(@RequestParam Long id){
        Event event = eventService.getEvent(id);
        return event;
    }


    @GetMapping("/geteventwithname")
    public List<Event> getEventWithName (@RequestParam String name){
        List<Event> events = eventService.getEventWithName(name);
        return events;
    }

    @GetMapping("/getnumberreservationsforevent")
    public int getNumberReservationsForEvent(@RequestParam Long eventId){
        return eventService.getNumberReservationsForEvent(eventId);
    }

    @GetMapping("/getallratingsforevent")
    public HashMap<String, Integer> getAllRatingsForEvent(@RequestParam Long eventId){
        return eventService.getAllRatingsForEvent(eventId);
    }

    @PostMapping("/addattendance")
    public void AddAttendance(@RequestParam Long eventid, @RequestBody Attendance attendance) {
        eventService.addAttendance(eventid, attendance);
    }
}
