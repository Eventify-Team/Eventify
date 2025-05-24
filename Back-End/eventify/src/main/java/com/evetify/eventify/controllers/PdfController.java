package com.evetify.eventify.controllers;

import com.evetify.eventify.services.TicketPdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    TicketPdfService ticketPdfService;
    @GetMapping("/generate")
    public ResponseEntity<String> pdf(String name, String surname, String nameEvent, String location, String description, Date date, double fee, Long attendanceId) {
        try {
            ticketPdfService.generateTicket(name, surname, nameEvent, location, description, date, fee, attendanceId    );
            return ResponseEntity.ok("Το εισιτήριο δημιουργήθηκε στην επιφάνεια εργασίας!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Σφάλμα: " + e.getMessage());
        }
    }
}
