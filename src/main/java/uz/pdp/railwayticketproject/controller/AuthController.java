package uz.pdp.railwayticketproject.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.railwayticketproject.dto.auth.LoginDto;
import uz.pdp.railwayticketproject.dto.auth.SetMailDto;
import uz.pdp.railwayticketproject.dto.auth.SignUpDto;
import uz.pdp.railwayticketproject.dto.auth.UserUpdateDto;
import uz.pdp.railwayticketproject.entity.User;

import uz.pdp.railwayticketproject.response.JwtResponse;
import uz.pdp.railwayticketproject.service.AuthenticationService;
import uz.pdp.railwayticketproject.service.impl.EmailService;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    private final EmailService emailService;

    @PostMapping("/signup")
    public User signUp(@RequestBody SignUpDto signUpDto){
        return authenticationService.saveUser(signUpDto);
    }

    @PutMapping("/update")
    private User userUpdate(@RequestBody UserUpdateDto updateDto){
        return authenticationService.updateUser(updateDto);
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginDto loginDto){
        return authenticationService.loginAccount(loginDto);
    }

    @PostMapping("/verification")
    public ResponseEntity<?> forgotPassword(@RequestBody SetMailDto setMailDto){
        return emailService.sendEmail(setMailDto);
    }

}
