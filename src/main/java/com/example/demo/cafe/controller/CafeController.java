package com.example.demo.cafe.controller;

import com.example.demo.cafe.dto.CafeDto;
import com.example.demo.cafe.service.CafeService;
import com.example.demo.constant.dto.ApiResponse;
import com.example.demo.constant.enums.CustomResponseCode;
import com.example.demo.security.service.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
public class CafeController {

    private final CafeService cafeService;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<CafeDto.CafeRegisterResponseDto>> registerCafe(
            @RequestPart(value = "cafeReg") CafeDto.CafeRegisterRequestDto requestDto,
            @RequestPart(value = "cafeRepImg") MultipartFile cafeRepImgFile,
            @RequestPart(value = "studyImg") MultipartFile studyImgFile
            ) throws IOException {
//        String userName = jwtUtils.getUserName(headers);
//        // 사용자 이름을 사용하여 사용자 ID를 조회
//        Integer userId = usersService.findUserIdByUsername(userName); // 예시 메소드, 실제 구현 필요
//        requestDto.setUserId(userId);

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

        CafeDto.CafeRegisterResponseDto responseDto = cafeService.registerCafe(requestDto);
        return ResponseEntity.ok().body(ApiResponse.createSuccess(responseDto, CustomResponseCode.SUCCESS));
    }

}