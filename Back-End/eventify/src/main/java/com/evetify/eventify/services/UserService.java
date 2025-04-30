package com.evetify.eventify.services;

import com.evetify.eventify.models.Event;
import com.evetify.eventify.models.User;
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

    @Autowired UserRepository userRepository;



    public User addUser(User user){
        userRepository.save(user);
        return user;
    }

    public List<User> RemoveUser(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.deleteById(id);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
        }

        return userRepository.findAll();

    }

    public User updateUser(Long id, String name, String surname, String username, String email, String password){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found!");
        User usr = user.get();
        if(name != null)
            usr.setName(name);
        if(surname != null)
            usr.setSurname(surname);
        if(username != null)
            usr.setUsername(username);
        if(email != null)
            usr.setEmail(email);
        if(password != null)
            usr.setPassword(password);

        userRepository.save(usr);
        return usr;
    }

    public User getUser(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
        }
    }

    public ArrayList<User> getAllUsers(){
        return (ArrayList<User>) userRepository.findAll();
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

}
