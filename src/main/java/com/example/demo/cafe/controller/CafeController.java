package com.example.demo.cafe.controller;

import com.example.demo.cafe.dto.CafeDto;
import com.example.demo.cafe.dto.CafeImgDto;
import com.example.demo.cafeFeature.dto.CafeFeatureDto;
import com.example.demo.cafeFeature.service.CafeFeatureService;
import com.example.demo.cafe.service.CafeImgService;
import com.example.demo.cafe.service.CafeService;
import com.example.demo.constant.dto.ApiResponse;
import com.example.demo.constant.enums.CustomResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
public class CafeController {

    private final CafeService cafeService;
    private final CafeImgService cafeImgService;
    private final CafeFeatureService cafeFeatureService;

    @PostMapping("/manager/cafe/register")
    public ResponseEntity<ApiResponse<String>> registerCafe (
            @RequestPart(value = "cafeReg") CafeDto.CafeRegisterRequestDto requestDto,
            @RequestPart(value = "cafeRepImg", required = false) MultipartFile cafeRepImgFile,
            @RequestPart(value = "cafeStudyImg", required = false) MultipartFile studyImgFile, Authentication authentication,
            @RequestPart(value = "cafeImgs", required = false) MultipartFile[] cafeDetailImgFiles,
            @RequestPart(value = "cafeFeature") CafeFeatureDto.CafeFeatureRequestDto cafeFeatureRequestDto) throws IOException {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();

        if (cafeDetailImgFiles != null && cafeDetailImgFiles.length > 5) {
            return ResponseEntity.badRequest().body(ApiResponse.createError(CustomResponseCode.IMG_OVER_SELECT));
        }

        if (cafeRepImgFile != null && !cafeRepImgFile.isEmpty()) {
            requestDto.setCafeRepImg(cafeRepImgFile.getBytes());
        }else {
            requestDto.setStudyImg(null);
        }

        if (studyImgFile != null && !studyImgFile.isEmpty()) {
            requestDto.setStudyImg(studyImgFile.getBytes());
        } else {
            requestDto.setStudyImg(null);
        }

        List<CafeImgDto.CafeRegisterImgRequestDto> imgDtos = new ArrayList<>();
        for (MultipartFile imgFile : cafeDetailImgFiles) {
            if (imgFile != null && !imgFile.isEmpty()) {
                CafeImgDto.CafeRegisterImgRequestDto imgDto = new CafeImgDto.CafeRegisterImgRequestDto();
                imgDto.setCafeDetailImg(imgFile.getBytes());
                imgDtos.add(imgDto);
            }
        }

        cafeService.registerCafe(requestDto, userName, cafeRepImgFile, studyImgFile);
        cafeImgService.insertCafeImg(imgDtos, userName);
        cafeFeatureService.insertCafeFeatures(cafeFeatureRequestDto, userName);

        return ResponseEntity.ok().body(ApiResponse.createSuccessWithNoContent(CustomResponseCode.SUCCESS));
    }

    @GetMapping("/manager/cafe/basic")
    public ResponseEntity<ApiResponse<CafeDto.CafeReadBasicResponseDto>> readCafeBasic(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        log.info(userName);
        CafeDto.CafeReadBasicResponseDto result = cafeService.findCafeBasicByUserId(userName);
        return ResponseEntity.ok().body(ApiResponse.createSuccess(result,CustomResponseCode.SUCCESS));
    }

    @GetMapping("/manager/cafe/details")
    public ResponseEntity<ApiResponse<CafeDto.CafeDetailsResponseWrapper>> readCafeDetails(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();

        CafeDto.CafeReadDetailResponseDto detailResult = cafeService.findCafeDetailByUserId(userName);
        CafeFeatureDto.CafeFeatureResponseDto featureResult = cafeFeatureService.selectCafeFeature(userName);
        CafeDto.CafeDetailsResponseWrapper wrapper = new CafeDto.CafeDetailsResponseWrapper(detailResult, featureResult);
        return ResponseEntity.ok().body(ApiResponse.createSuccess(wrapper,CustomResponseCode.SUCCESS));
    }

    @GetMapping("/manager/cafe/setting")
    public ResponseEntity<ApiResponse<CafeDto.CafeReadSettingResponseDto>> readCafeSetting(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();

        CafeDto.CafeReadSettingResponseDto result =  cafeService.findCafeSettingByUserId(userName);
        return ResponseEntity.ok().body(ApiResponse.createSuccess(result,CustomResponseCode.SUCCESS));
    }

    @GetMapping("/user/cafe/{cafeId}")
    public ResponseEntity<ApiResponse<CafeDto.CafeSearchDetailResponseDto>> readCafeDetail(
            @PathVariable int cafeId,
            Authentication authentication){

        CafeDto.CafeSearchDetailResponseDto detail = cafeService.searchCafeDetail(cafeId);
        return ResponseEntity.ok().body(ApiResponse.createSuccess(detail,CustomResponseCode.SUCCESS));
    }


}