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

    @GetMapping("/getAllEvents")
    public List<Event> getAllEvents(){
        List<Event> events = eventService.getAllEvents();
        return events;
    }

    @GetMapping("/getEvent")
    public Event getEvent(@RequestParam Long eventId){
        Event event = eventService.getEvent(eventId);
        return event;
    }

    @GetMapping("/getEventWithName")
    public List<Event> getEventWithName (@RequestParam String eventName){
        List<Event> events = eventService.getEventWithName(eventName);
        return events;
    }

    @GetMapping("/getNumberReservationsForEvent")
    public int getNumberReservationsForEvent(@RequestParam Long eventId){
        return eventService.getNumberReservationsForEvent(eventId);
    }

    @GetMapping("/getAllRatingsForEvent")
    public HashMap<String, Integer> getAllRatingsForEvent(@RequestParam Long eventId){
        return eventService.getAllRatingsForEvent(eventId);
    }

    @PostMapping("/addAttendance")
    public void AddAttendance(@RequestParam Long eventid, @RequestBody Attendance attendance) {
        eventService.addAttendance(eventid, attendance);
    }

    @PostMapping("/addEvent")
    public void addEvent(@RequestBody Event event){
        eventService.addEvent(event);
    }
}
