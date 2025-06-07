package com.evetify.eventify;


import com.evetify.eventify.models.Admin;
import com.evetify.eventify.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    @Autowired
    private AdminRepository adminRepository;

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
}