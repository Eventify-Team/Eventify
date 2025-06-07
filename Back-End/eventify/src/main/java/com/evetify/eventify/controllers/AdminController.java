package com.evetify.eventify.controllers;

import com.evetify.eventify.models.*;
import com.evetify.eventify.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Autowired
    ReservationService reservationService;


    @Autowired
    JwtService jwtService;


    @PostMapping("/addAdmin")
    public void addAdmin(@RequestBody Admin admin){
        adminService.addAdmin(admin);
    }

    @DeleteMapping("/deleteAdmin")
    public void removeAdmin(@RequestParam Long adminId){
        adminService.RemoveAdmin(adminId);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestParam Long userId) {
        userService.RemoveUser(userId);
    }


    @GetMapping("/getUser")
    public User getUser(@RequestParam Long userId){
        return userService.getUser(userId);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/getUserByUsername")
    public User getUserByUsername(@RequestParam String username){
        return userService.getUserByUsername(username);
    }

    @PutMapping("/updateAdmin")
    public Admin updateAdmin(@RequestParam Long adminId, @RequestParam (required = false) String lastName,
                             @RequestParam (required = false) String firstName,
                           @RequestParam (required = false) String password,
                           @RequestParam (required = false) String email){
        Admin admin = adminService.updateAdmin(adminId, lastName, firstName, password,email);
        return admin;
    }

    @GetMapping("/getAdminByUsername")
    public Admin getAdminByUsername(@RequestParam String adminUsername){
        return adminService.getAdminByUsername(adminUsername);
    }

    @PostMapping("/addEvent")
    public void addEvent(@RequestBody Event event){
        eventService.addEvent(event);
    }

    // Endpoint για upload εικόνας
    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // Δημιουργία φακέλου αν δεν υπάρχει
            Path uploadPath = Paths.get("eventify/uploaded-images/");
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Αποθήκευση αρχείου με το original όνομα
            String filename = file.getOriginalFilename();
            Path filePath = uploadPath.resolve(filename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Επιστροφή URL ή path της εικόνας
            String fileUrl = "/images/" + filename;
            return ResponseEntity.ok(fileUrl);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }

    @PutMapping("/updateEvent")
    public Event updateEvent (@RequestParam Long eventId, @RequestParam (required = false) String name,
                              @RequestParam (required = false) String description,
                              @RequestParam (required = false) Integer duration,
                              @RequestParam (required = false) String location,
                              @RequestParam (required = false) Integer capacity,
                              @RequestParam (required = false) String date,
                              @RequestParam (required = false) String time,
                              @RequestParam (required = false) Double fee){
        Event event = eventService.updateEvent(eventId, name, description, duration, location, capacity, date, time, fee);
        return event;
    }

    @DeleteMapping("/deleteEvent")
    public List<Event> deleteEvent(@RequestParam Long eventId){
        List<Event> events = eventService.removeEvent(eventId);
        return events;
    }

    @GetMapping("/getAllAdmins")
    public List<Admin> getAllAdmins(){
        List<Admin> admins = adminService.getAllAdmins();
        return admins;
    }


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

    @GetMapping("/getReservation")
    public Reservation getReservation(@RequestParam Long reservationId){
        return reservationService.getReservation(reservationId);
    }

    @GetMapping("/getAllReservations")
    public List<Reservation> getAllReservations(){
        List<Reservation> reservationList = reservationService.getAllReservations();
        return reservationList;
    }

    @GetMapping("/getAllReservationsForEvent")
    public List<Reservation> getAllReservationsForEvent(@RequestParam Long eventId){
        List<Reservation> reservationList = reservationService.getAllReservationsForEvent(eventId);
        return reservationList;
    }

    @GetMapping("/getAllRatingsForEvent")
    public HashMap<String, Integer> getRatingsForEvent(@RequestParam Long eventId){
        HashMap<String, Integer> ratings = eventService.getAllRatingsForEvent(eventId);
        return ratings;
    }

    @GetMapping("/getUsersForEvent")
    public List<User> getUsersForEvent(@RequestParam Long eventId){
        return eventService.getAllUsersForEvent(eventId);
    }

    //για το login
    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Admin admin = adminService.getAdminByUsername(username);

        if (admin != null && admin.getPassword().equals(password)) {
            String token = jwtService.generateToken(username, "ADMIN");

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("role", "ADMIN");
            response.put("username", username);
            return ResponseEntity.ok(response);

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}





