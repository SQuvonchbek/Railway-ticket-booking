package uz.pdp.railwayticketproject.service;

import uz.pdp.railwayticketproject.dto.auth.LoginDto;
import uz.pdp.railwayticketproject.dto.auth.SignUpDto;
import uz.pdp.railwayticketproject.dto.auth.UserUpdateDto;
import uz.pdp.railwayticketproject.entity.User;
import uz.pdp.railwayticketproject.response.JwtResponse;

public interface AuthenticationService {

    User saveUser(SignUpDto signUpDto);
    User updateUser(UserUpdateDto userUpdateDto);
    JwtResponse loginAccount(LoginDto loginDto);


}
