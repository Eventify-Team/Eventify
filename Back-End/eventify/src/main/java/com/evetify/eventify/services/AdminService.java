package com.evetify.eventify.services;

import com.evetify.eventify.models.Admin;
import com.evetify.eventify.models.User;
import com.evetify.eventify.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired AdminRepository adminRepository;

    public Admin addAdmin(Admin admin){
        adminRepository.save(admin);
        return admin;
    }

    public List<Admin> RemoveAdmin(Long adminId){
        Optional<Admin> admin = adminRepository.findById(adminId);
        if(admin.isPresent()){
            adminRepository.deleteById(adminId);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found!");
        }

        return adminRepository.findAll();

    }

    public Admin getAdminByUsername(String adminUsername) {
        return adminRepository.findByUsername(adminUsername);
    }

    public List<Admin> getAllAdmins() {
        return (List<Admin>) adminRepository.findAll();
    }



    public Admin updateAdmin(Long adminId, String lastName, String firstName, String password, String email){
        Optional<Admin> optionalAdmin = adminRepository.findById(adminId);
        if(!optionalAdmin.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin Not Found!");
        Admin admin = optionalAdmin.get();
        if(lastName != null)
            admin.setLastName(lastName);
        if(firstName != null)
            admin.setFirstName(firstName);
        if(password != null)
            admin.setPassword(password);
        if(email != null)
            admin.setEmail(email);

        adminRepository.save(admin);
        return admin;
    }
}
