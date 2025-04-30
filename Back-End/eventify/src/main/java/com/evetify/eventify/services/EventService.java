package com.evetify.eventify.services;

import com.evetify.eventify.models.Event;
import com.evetify.eventify.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    public ArrayList<Event> getAllEvents(){
        return (ArrayList<Event>) eventRepository.findAll();
    }

    public Event getEvent(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if(event.isPresent())
            return event.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found");
    }

    public List<Event> removeEvent(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if(!event.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found");
        eventRepository.deleteById(id);

        return eventRepository.findAll();
    }

    public Event addEvent(Event event){
        eventRepository.save(event);
        return event;
    }

    public Event updateEvent(Long id, String name, String description, Integer duration, String location, Integer capacity, Date aDate, Double fee){
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if(!optionalEvent.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found");
        Event event = optionalEvent.get();
        if(name != null)
            event.setName(name);
        if(description != null)
            event.setDescription(description);
        if(duration != null)
            event.setDuration(duration);
        if(location != null)
            event.setLocation(location);
        if(capacity != null)
            event.setCapacity(capacity);
        if(aDate != null)
            event.setDate(aDate);
        if(fee != null)
            event.setFee(fee);

        eventRepository.save(event);
        return event;
    }

    public List<Event> getEventWithName(String name) {
        return eventRepository.findByName(name);
    }



}
