package com.example.demo.cafe.controller;

import com.example.demo.cafe.dto.CafeDto;
import com.example.demo.cafe.dto.CafeUpdateDto;
import com.example.demo.cafe.service.CafeUpdateService;
import com.example.demo.cafeFeature.dto.CafeFeatureDto;
import com.example.demo.cafeFeature.service.CafeFeatureService;
import com.example.demo.constant.dto.ApiResponse;
import com.example.demo.constant.enums.CustomResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.Value;
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
    private final CafeFeatureService cafeFeatureService;

    @PatchMapping("/manager/cafe/edit/tel")
    public ResponseEntity<ApiResponse<CafeUpdateDto.CafeTelResponseDto>> updateCafeTel(@RequestBody CafeUpdateDto.CafeTelRequestDto cafeTel, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();

        CafeUpdateDto.CafeTelResponseDto result = cafeUpdateService.cafeTelUpdate(cafeTel, userName);

        return ResponseEntity.ok().body(ApiResponse.createSuccess(result,CustomResponseCode.SUCCESS));
    }

    @PatchMapping("/manager/cafe/edit/location")
    public ResponseEntity<ApiResponse<CafeUpdateDto.CafeLocationResponseDto>> updateCafeAddress(
            @RequestBody CafeUpdateDto.CafeLocationRequestDto cafeAddress,
            Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();

        CafeUpdateDto.CafeLocationResponseDto result = cafeUpdateService.cafeAddressUpdate(cafeAddress, userName);

        return ResponseEntity.ok().body(ApiResponse.createSuccess(result,CustomResponseCode.SUCCESS));
    }

    @PatchMapping("/manager/cafe/edit/time")
    public ResponseEntity<ApiResponse<CafeUpdateDto.CafeTimeResponseDto>> updateCafeAddress(
            @RequestBody CafeUpdateDto.CafeTimeRequestDto cafeTime,
            Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();

        CafeUpdateDto.CafeTimeResponseDto result = cafeUpdateService.cafeTimeUpdate(cafeTime, userName);

        return ResponseEntity.ok().body(ApiResponse.createSuccess(result,CustomResponseCode.SUCCESS));
    }

    @PostMapping("/manager/cafe/edit/feature")
    public ResponseEntity<ApiResponse<CafeFeatureDto.CafeFeatureResponseDto>> updateCafeFeature(
            @RequestBody CafeFeatureDto.CafeFeatureRequestDto cafeFeature,
            Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();

        cafeFeatureService.deleteFeatures(userName);
        CafeFeatureDto.CafeFeatureResponseDto result = cafeFeatureService.insertCafeFeatures(cafeFeature,userName);

        return ResponseEntity.ok().body(ApiResponse.createSuccess(result,CustomResponseCode.SUCCESS));
    }
    @PatchMapping("/manager/cafe/edit/study")
    public ResponseEntity<ApiResponse<CafeUpdateDto.CafeStudyResponseDto>> updateCafeAddress(
            @RequestBody CafeUpdateDto.CafeStudyRequestDto cafeStudy,
            Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();

        CafeUpdateDto.CafeStudyResponseDto result = cafeUpdateService.cafeStudyUpdate(cafeStudy, userName);

        return ResponseEntity.ok().body(ApiResponse.createSuccess(result,CustomResponseCode.SUCCESS));
    }
}
