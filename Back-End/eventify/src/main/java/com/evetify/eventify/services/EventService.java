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

import javax.transaction.Transactional;
import java.util.*;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    AttendanceRepository attendanceRepository;



    public List<Event> getAllEvents(){
        return (List<Event>) eventRepository.findAll();
    }

    public Event getEvent(Long eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        if(event.isPresent())
            return event.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found");
    }

    @Transactional
    public List<Event> removeEvent(Long eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        if(!event.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found");

        attendanceRepository.deleteAllByEvent(event.get());
        eventRepository.deleteById(eventId);

        return eventRepository.findAll();
    }

    public Event addEvent(Event event){
        eventRepository.save(event);
        return event;
    }

    public Event updateEvent(Event event){
        Optional<Event> optionalEvent = eventRepository.findById(event.getId());
        if(!optionalEvent.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found");
        Event event2 = optionalEvent.get();
        if(event.getName() != null)
            event2.setName(event.getName());
        if(event.getDescription() != null)
            event2.setDescription(event.getDescription() );
        if(event.getDuration() != null)
            event2.setDuration(event.getDuration() );
        if(event.getLocation() != null)
            event2.setLocation(event.getLocation());
        if(event.getCapacity()!= null)
            event2.setCapacity(event.getCapacity());
        if(event.getDate() != null)
            event2.setDate(event.getDate());
        if(event2.getTime() != null)
            event2.setTime(event.getTime());
        if(event.getFee() != null)
            event2.setFee(event.getFee());

        eventRepository.save(event2);
        return event2;
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

    public Integer getUserRatingForEvent(Long eventId, Long userId){
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        Integer rating = 0;
        if(!optionalEvent.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found");
        Event event = optionalEvent.get();
        Optional<User> userOptional = userRepository.findById(userId);
        if(!userOptional.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
        User user = userOptional.get();
        List<Attendance> attendances = event.getAttendances();
        for(Attendance att : attendances){
            if(att.getRating()!=null){
                if(att.getUserId().equals(userId) && att.getEventId().equals(eventId)){
                    rating = att.getRating();
                }
            }
        }
        return rating;
    }

    public double getAverageRatingForEvent(Long eventId){
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if(!optionalEvent.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found");
        Event event = optionalEvent.get();
        List<Attendance> attendances = event.getAttendances();
        double sum=0.0;
        int count=0;
        for(Attendance att : attendances){
            Integer score = att.getRating();
            if (score != null) {
                sum += score;
                count++;
            }
        }
        if (count == 0) return 0.0;
        return sum / (double) count;
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

//    public void addAttendance(Long eventId, Attendance a){
//        Optional<Event> optionalEvent = eventRepository.findById(eventId);
//        if(!optionalEvent.isPresent())
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found");
//        Event event = optionalEvent.get();
//        List<Attendance> attendances = event.getAttendances();
//        attendances.add(a);
//        event.setAttendances(attendances);
//        eventRepository.save(event);
//    }
}