package com.example.demo.cafe.controller;

import com.example.demo.cafe.dto.CafeDto;
import com.example.demo.cafe.service.CafeService;
import com.example.demo.constant.dto.ApiResponse;
import com.example.demo.constant.enums.CustomResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
public class CafeController {

    private final CafeService cafeService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<CafeDto.CafeRegisterResponseDto>> registerCafe(
            @RequestBody CafeDto.CafeRegisterRequestDto requestDto) {
        CafeDto.CafeRegisterResponseDto cafeRegisterResponseDto = cafeService.registerCafe(requestDto);
        return ResponseEntity.ok().body(ApiResponse.createSuccess(cafeRegisterResponseDto, CustomResponseCode.SUCCESS));
    }
}