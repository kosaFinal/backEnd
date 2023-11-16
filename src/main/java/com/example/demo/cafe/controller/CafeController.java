package com.example.demo.cafe.controller;

import com.example.demo.cafe.dto.CafeDto;
import com.example.demo.cafe.dto.CafeImgDto;
import com.example.demo.cafe.entity.CafeFeature;
import com.example.demo.cafe.service.CafeFeatureService;
import com.example.demo.cafe.service.CafeImgService;
import com.example.demo.cafe.service.CafeService;
import com.example.demo.constant.dto.ApiResponse;
import com.example.demo.constant.enums.CustomResponseCode;
import com.example.demo.security.service.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/cafe")
@Slf4j
public class CafeController {

    private final CafeService cafeService;
    private final CafeImgService cafeImgService;
    private final CafeFeatureService cafeFeatureService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerCafe (
            @RequestPart(value = "cafeReg") CafeDto.CafeRegisterRequestDto requestDto,
            @RequestPart(value = "cafeRepImg", required = false) MultipartFile cafeRepImgFile,
            @RequestPart(value = "cafeStudyImg", required = false) MultipartFile studyImgFile, Authentication authentication,
            @RequestPart(value = "cafeImgs", required = false) MultipartFile[] cafeDetailImgFiles,
            @RequestPart(value = "cafeFeature")CafeDto.CafeFeatureRequestDto cafeFeatureRequestDto) throws IOException {

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



}