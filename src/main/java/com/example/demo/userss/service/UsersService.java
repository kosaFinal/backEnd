package com.example.demo.userss.service;

import com.example.demo.userss.dto.UsersDto;
import org.springframework.stereotype.Service;


public interface UsersService {

    UsersDto.UserLoginResponseDto login(UsersDto.UserLoginRequestDto userLoginRequestDto);

}
