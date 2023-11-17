package com.example.demo.cafe.controller;

import com.example.demo.cafe.dto.CafeDto;
import com.example.demo.cafe.dto.CafeImgDto;
import com.example.demo.cafeFeature.dto.CafeFeatureDto;
import com.example.demo.cafeFeature.service.CafeFeatureService;
import com.example.demo.cafe.service.CafeImgService;
import com.example.demo.cafe.service.CafeService;
import com.example.demo.cafeTable.service.CafeTableService;
import com.example.demo.constant.dto.ApiResponse;
import com.example.demo.constant.enums.CustomResponseCode;
import com.example.demo.reservation.reservation.dto.ReservationDto;
import com.example.demo.reservation.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
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
    private final ReservationService reservationService;

    @PostMapping("/manager/cafe/register")
    public ResponseEntity<ApiResponse<String>> registerCafe (
            @RequestPart(value = "cafeReg") CafeDto.CafeRegisterRequestDto requestDto,
            @RequestPart(value = "cafeRepImg", required = false) MultipartFile cafeRepImgFile,
            @RequestPart(value = "cafeStudyImg", required = false) MultipartFile studyImgFile, Authentication authentication,
            @RequestPart(value = "cafeImgs", required = false) MultipartFile[] cafeDetailImgFiles,
            @RequestPart(value = "cafeFeature") CafeFeatureDto.CafeFeatureRequestDto cafeFeatureRequestDto) throws IOException {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        log.info("카페 등록 페이지!");

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
        log.info("카페 베이직 페이지!");
        CafeDto.CafeReadBasicResponseDto result = cafeService.findCafeBasicByUserId(userName);
        return ResponseEntity.ok().body(ApiResponse.createSuccess(result,CustomResponseCode.SUCCESS));
    }

    @GetMapping("/manager/cafe/details")
    public ResponseEntity<ApiResponse<CafeDto.CafeDetailsResponseWrapper>> readCafeDetails(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        log.info("카페 디테일 페이지!");
        CafeDto.CafeReadDetailResponseDto detailResult = cafeService.findCafeDetailByUserId(userName);
        CafeFeatureDto.CafeFeatureResponseDto featureResult = cafeFeatureService.selectCafeFeature(userName);
        CafeDto.CafeDetailsResponseWrapper wrapper = new CafeDto.CafeDetailsResponseWrapper(detailResult, featureResult);
        return ResponseEntity.ok().body(ApiResponse.createSuccess(wrapper,CustomResponseCode.SUCCESS));
    }

    @GetMapping("/manager/cafe/setting")
    public ResponseEntity<ApiResponse<CafeDto.CafeSettingResponseWrapper>> readCafeSetting(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        log.info("카페 세팅 페이지!");
        ReservationDto.RevCafeInfoResponseDto CafeTableResult = reservationService.getRevCafeInfo(cafeImgService.findCafeIdByUserName(userName));
        CafeDto.CafeReadSettingResponseDto CafeSettingResult = cafeService.findCafeSettingByUserId(userName);
        CafeDto.CafeSettingResponseWrapper wrapper = new CafeDto.CafeSettingResponseWrapper(CafeSettingResult, CafeTableResult);

        return ResponseEntity.ok().body(ApiResponse.createSuccess(wrapper,CustomResponseCode.SUCCESS));
    }

    @GetMapping("/user/cafe/{cafeId}")
    public ResponseEntity<ApiResponse<CafeDto.CafeSearchDetailResponseDto>> readCafeDetail(
            @PathVariable int cafeId,
            Authentication authentication){
        log.info("유저 - 카페 상세 정보!");
        CafeDto.CafeSearchDetailResponseDto detail = cafeService.searchCafeDetail(cafeId);
        return ResponseEntity.ok().body(ApiResponse.createSuccess(detail,CustomResponseCode.SUCCESS));
    }

    @DeleteMapping("/manager/cafe/delete")
    public ResponseEntity<ApiResponse>deleteCafe(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        log.info("카페 삭제!");
        cafeService.deleteCafe(userName);
        return ResponseEntity.ok().body(ApiResponse.createSuccessWithNoContent(CustomResponseCode.SUCCESS));
    }



}