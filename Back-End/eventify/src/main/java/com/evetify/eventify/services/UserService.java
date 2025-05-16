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
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    EventRepository eventRepository;



    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

    public List<User> RemoveUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.deleteById(userId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
        }

        return userRepository.findAll();

    }

    public User updateUser(Long userId, String name, String surname, String username, String email, String password) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found!");
        User usr = user.get();
        if (name != null)
            usr.setName(name);
        if (surname != null)
            usr.setSurname(surname);
        if (username != null)
            usr.setUsername(username);
        if (email != null)
            usr.setEmail(email);
        if (password != null)
            usr.setPassword(password);

        userRepository.save(usr);
        return usr;
    }

    public User getUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
        }
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void addAttendance(Long userId, Attendance a) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
        User user = optionalUser.get();
        List<Attendance> attendances = user.getAttendances();
        attendances.add(a);

        userRepository.save(user);
    }

    public void addRatingForEvent(Long userId, Long eventId, Integer score) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        boolean flag = false;
        if (!optionalEvent.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found");
        Event event = optionalEvent.get();
        List<Attendance> attendances = event.getAttendances();
        for (Attendance att : attendances) {
            if (att.getUserId().equals(userId)) {
                flag = true;
                if (att.getEventId().equals(eventId))
                    att.setRating(score);
            }
        }
        if(!flag)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
        eventRepository.save(event);
    }

    public void cancelReservation(Long userId, Long eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        boolean flag = false;
        if (!optionalEvent.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found");
        Event event = optionalEvent.get();
        List<Attendance> attendances = event.getAttendances();
        for (Attendance att : attendances) {
            if (att.getUserId().equals(userId)) {
                flag = true;
                if (att.getEventId().equals(eventId)) {
                    att.getReservation().setValid(false);
                    attendances.remove(att);
                }
            }
        }
        if(!flag)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
        eventRepository.save(event);
    }
}
