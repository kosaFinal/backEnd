package com.example.demo.cafe.controller;

import com.example.demo.cafe.dto.CafeDto;
import com.example.demo.cafe.dto.CafeUpdateDto;
import com.example.demo.cafe.service.CafeUpdateService;
import com.example.demo.constant.dto.ApiResponse;
import com.example.demo.constant.enums.CustomResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CafeUpdateController {

    private final CafeUpdateService cafeUpdateService;

    @PatchMapping("/manager/cafe/edit/tel")
    public ResponseEntity<ApiResponse<CafeUpdateDto.CafeTelResponseDto>> updateCafeTel(@RequestBody CafeUpdateDto.CafeTelRequestDto cafeTel, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();

        CafeUpdateDto.CafeTelResponseDto result = cafeUpdateService.cafeUpdate(cafeTel, userName);


        return ResponseEntity.ok().body(ApiResponse.createSuccess(result,CustomResponseCode.SUCCESS));
    }



}
