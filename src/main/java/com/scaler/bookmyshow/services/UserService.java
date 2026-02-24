package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.models.User;
import com.scaler.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User signUp(String email,String password){
        User user=new User();
        user.setEmail(email);
        user.setPassword(password);
        return userRepository.save(user);
    }
}

