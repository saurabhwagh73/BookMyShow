package com.scaler.bookmyshow.controllers;

import com.scaler.bookmyshow.dtos.ResponseStatus;
import com.scaler.bookmyshow.dtos.SignUpRequestDto;
import com.scaler.bookmyshow.dtos.SignUpResponseDto;
import com.scaler.bookmyshow.models.User;
import com.scaler.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto){
        SignUpResponseDto signUpResponseDto=new SignUpResponseDto();

        User user=userService.signUp(signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
        signUpResponseDto.setId(user.getId());
        signUpResponseDto.setResponseStatus(ResponseStatus.SUCCESS);

        return signUpResponseDto;
    }
}
