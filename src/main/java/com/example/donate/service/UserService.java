package com.example.donate.service;

import com.example.donate.model.User;
import com.example.donate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user){
        userRepository.save(user);
    }

    public User findUserByUserName(String userName) {
        User user = userRepository.getUserByUserName(userName);
        if(user == null) {
            throw new UsernameNotFoundException("Unable to Find the User");
        }
        return user;
    }

    public String uniqueEmail(String userName) {
        User user = userRepository.getUserByUserName(userName);
        if(user == null) {
            return "OK";
        }
        return "DUPLICATE";
    }
}
