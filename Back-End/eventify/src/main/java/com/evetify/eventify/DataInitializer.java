package com.evetify.eventify;


import com.evetify.eventify.models.Admin;
import com.evetify.eventify.models.Event;
import com.evetify.eventify.repositories.AdminRepository;
import com.evetify.eventify.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private EventRepository eventRepository;

    @PostConstruct
    public void initAdmin() {
        if (adminRepository.findByUsername("admin") == null) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword("admin123");
            admin.setFirstName("Super");
            admin.setLastName("Admin");
            admin.setEmail("admin@eventify.com");

            adminRepository.save(admin);
            System.out.println("Default admin created");
        } else {
            System.out.println("Admin already exists");
        }
    }

    @PostConstruct
    public void initEvents() {
        Event event1 = new Event();
        event1.setName("Music Festival");
        event1.setDescription("A summer music festival with local bands");
        event1.setLocation("Athens");
        event1.setDate("2025-07-15");
        event1.setTime("18:00");
        event1.setDuration(240);
        event1.setFee(20.00);
        event1.setCapacity(500);
        event1.setImageURL("https://www.clickatlife.gr/fu/t/214751/1200/10000/00000000005efa7c/1/primer-music-festival.jpg");
        System.out.println("Default event1 created");
        eventRepository.save(event1);

        Event event2 = new Event();
        event2.setName("Tech Meetup");
        event2.setDescription("Networking and presentations from tech startups.");
        event2.setLocation("Thessaloniki");
        event2.setDate("2025-06-30");
        event2.setTime("17:00");
        event2.setDuration(180);
        event2.setFee(0.00);
        event2.setCapacity(150);
        event2.setImageURL("https://pbs.twimg.com/profile_images/1618167601246396419/7Tv0_LH3_400x400.jpg");
        System.out.println("Default event2 created");
        eventRepository.save(event2);

        Event event3 = new Event();
        event3.setName("Metallica");
        event3.setDescription("M72 WORLD TOUR");
        event3.setLocation("Olympic Athletic Center of Athens(OAKA)");
        event3.setDate("2025-08-20");
        event3.setTime("21:00");
        event3.setDuration(100);
        event3.setFee(80.00);
        event3.setCapacity(100000);
        event3.setImageURL("https://highpriority.gr/wp-content/uploads/2025/05/Metallica_851x315_HPP_SiteTickets.jpg");
        System.out.println("Default event3 created");
        eventRepository.save(event3);

        Event event4 = new Event();
        event4.setName("Lilo & Stitch");
        event4.setDescription("Science Fiction Comedy");
        event4.setLocation("Cinema Ellinis");
        event4.setDate("2025-07-01");
        event4.setTime("20:00");
        event4.setDuration(130);
        event4.setFee(5.00);
        event4.setCapacity(1);
        event4.setImageURL("https://file.hstatic.net/1000290074/article/review_phim_lilo_va_stitch_live_action_bf958951bfc74aafae78fbe2bbd00df9.png");
        System.out.println("Default event4 created");
        eventRepository.save(event4);
    }

}