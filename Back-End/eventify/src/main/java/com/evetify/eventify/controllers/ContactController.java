package com.evetify.eventify.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "http://localhost:3000") //
public class ContactController {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping
    public ResponseEntity<String> sendContactForm(@RequestBody ContactForm form) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("eventify2025uom@gmail.com");
            message.setSubject("New Contact Message");
            message.setText(
                    "Όνομα: " + form.getName() +
                            "\nΕπώνυμο: " + form.getSurname() +
                            "\nEmail: " + form.getEmail() +
                            "\n\nΜήνυμα:\n" + form.getMessage()
            );
            mailSender.send(message);
            return ResponseEntity.ok("Το μήνυμα στάλθηκε.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Αποτυχία αποστολής.");
        }
    }

    static class ContactForm {
        private String name;
        private String surname;
        private String email;
        private String message;

        // Getters & Setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getSurname() { return surname; }
        public void setSurname(String surname) { this.surname = surname; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
}
