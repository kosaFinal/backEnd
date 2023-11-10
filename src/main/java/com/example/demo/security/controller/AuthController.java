package com.example.demo.security.controller;

import com.example.demo.constant.dto.ApiResponse;
import com.example.demo.constant.enums.CustomResponseCode;
import com.example.demo.userss.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.security.service.JwtUtils;
import com.example.demo.userss.dto.UsersDto;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsersService usersService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UsersDto.UserLoginResponseDto>> login(@RequestBody UsersDto.UserLoginRequestDto userLoginRequestDto){
        log.info("들어옴!");
        UsersDto.UserLoginResponseDto userLoginResponseDto = usersService.login(userLoginRequestDto);
        return ResponseEntity.ok().body(ApiResponse.createSuccess(userLoginResponseDto, CustomResponseCode.SUCCESS));
    }
}
