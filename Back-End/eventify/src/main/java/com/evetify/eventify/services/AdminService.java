package com.evetify.eventify.services;

import com.evetify.eventify.models.Admin;
import com.evetify.eventify.models.Event;
import com.evetify.eventify.models.User;
import com.evetify.eventify.repositories.AdminRepository;
import com.evetify.eventify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired AdminRepository adminRepository;

    public Admin addAdmin(Admin admin){
        adminRepository.save(admin);
        return admin;
    }

    public List<Admin> RemoveAdmin(Long id){
        Optional<Admin> admin = adminRepository.findById(id);
        if(admin.isPresent()){
            adminRepository.deleteById(id);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found!");
        }

        return adminRepository.findAll();

    }

    public Admin updateAdmin(Long id, String name, String password, String email){
        Optional<Admin> admin = adminRepository.findById(id);
        if(!admin.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin Not Found!");
        Admin adm = admin.get();
        if(name != null)
            adm.setName(name);
        if(password != null)
            adm.setPassword(password);
        if(email != null)
            adm.setEmail(email);

        adminRepository.save(adm);
        return adm;
    }
}
