package com.evetify.eventify.services;

import com.evetify.eventify.models.Attendance;
import com.evetify.eventify.models.Event;
import com.evetify.eventify.models.User;
import com.evetify.eventify.repositories.AttendanceRepository;
import com.evetify.eventify.repositories.EventRepository;
import com.evetify.eventify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;


    public List<Event> getAllEvents(){
        return (List<Event>) eventRepository.findAll();
    }

    public Event getEvent(Long eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        if(event.isPresent())
            return event.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found");
    }

    public List<Event> removeEvent(Long eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        if(!event.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found");
        eventRepository.deleteById(eventId);

        return eventRepository.findAll();
    }

    public Event addEvent(Event event){
        eventRepository.save(event);
        return event;
    }

    public Event updateEvent(Long eventId, String name, String description, Integer duration, String location, Integer capacity, Date aDate, Double fee){
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
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

    public List<Event> getEventWithName(String eventName) {
        return eventRepository.findByName(eventName);
    }

    public int getNumberReservationsForEvent(Long eventId){
        int total = 0;
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if(!optionalEvent.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found");
        Event event = optionalEvent.get();
        List<Attendance> attendances = event.getAttendances();
        for(Attendance att : attendances){
            if(att.getReservation().isValid())
                total++;
        }
        return total;
    }
    public HashMap<String,Integer> getAllRatingsForEvent(Long eventId){
        HashMap<String,Integer> ratings= new HashMap<>();
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if(!optionalEvent.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found");
        Event event = optionalEvent.get();
        List<Attendance> attendances = event.getAttendances();
        for(Attendance att : attendances){
            if(att.getRating()!=null)
                ratings.put(att.getUsername(),att.getRating());
        }
        eventRepository.save(event);
        return ratings;
    }

    public void addAttendance(Long eventId, Attendance a){
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if(!optionalEvent.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found");
        Event event = optionalEvent.get();
        List<Attendance> attendances = event.getAttendances();
        attendances.add(a);
        event.setAttendances(attendances);
        eventRepository.save(event);
    }

    public List<User> getAllUsersForEvent(Long eventId){
        List<User> users = new ArrayList<>();
        Optional<Event> eventopt = eventRepository.findById(eventId);
        if(eventopt.isPresent()){
            Event event = eventopt.get();
            List<Attendance> attendances = event.getAttendances();
            for(Attendance att : attendances){
                Long usrId = att.getUserId();
                Optional<User> user = userRepository.findById(usrId);
                if(user.isPresent())
                    users.add(user.get());
                else
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found!");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found!");
        }
        return users;
    }

}
