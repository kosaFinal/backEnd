package com.example.demo.security.controller;

import com.example.demo.constant.dto.ApiResponse;
import com.example.demo.constant.enums.CustomResponseCode;
import com.example.demo.constant.exception.GeneralException;
import com.example.demo.userss.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.example.demo.security.service.JwtUtils;
import com.example.demo.userss.dto.UsersDto;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/exception/entrypoint")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    @GetMapping("/nullToken")
    public void nullTokenException(){
        throw new GeneralException(CustomResponseCode.NO_TOKEN);
    }

    @GetMapping("/expiredToken")
    public void expiredTokenException(){
        throw new GeneralException(CustomResponseCode.EXPIRED_JWT);
    }

    @GetMapping("/badToken")
    public void badTokenException(){
        throw new GeneralException(CustomResponseCode.BAD_JWT);
    }

}
