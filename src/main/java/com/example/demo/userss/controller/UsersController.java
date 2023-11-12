package com.example.demo.userss.controller;

import com.example.demo.constant.dto.ApiResponse;
import com.example.demo.constant.enums.CustomResponseCode;
import com.example.demo.userss.dto.UsersDto;
import com.example.demo.userss.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class UsersController {

    private final UsersService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UsersDto.UserSignupResponseDto>> signup(@RequestBody UsersDto.UserSignupRequestDto userSignupRequestDto){
        UsersDto.UserSignupResponseDto userSignupResponseDto = userService.signup(userSignupRequestDto);
        log.info(userSignupResponseDto.getUserName());
        log.info("apiresponse: "+ApiResponse.createSuccess(userSignupResponseDto, CustomResponseCode.SUCCESS));
        return ResponseEntity.ok().body(ApiResponse.createSuccess(userSignupResponseDto, CustomResponseCode.SUCCESS));
    }



}
