package com.example.demo.reservation.reservation.controller;

import com.example.demo.constant.dto.ApiResponse;
import com.example.demo.constant.enums.CustomResponseCode;
import com.example.demo.reservation.reservation.dto.ReservationDto;
import com.example.demo.reservation.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/reservation")
@Slf4j
public class UserReservationController {

    private final ReservationService reservationService;

    // 예약 생성
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Boolean>> createReservation(@RequestBody ReservationDto.UserReservationRequestDto userReservationRequestDto, Authentication authentication) {
        log.info("예약 생성 요청 받음");

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        Boolean check = reservationService.createReservation(userReservationRequestDto,username);
        return ResponseEntity.ok().body(ApiResponse.createSuccess(check, CustomResponseCode.SUCCESS));
    }

    @GetMapping("/time/{date}/{tableId}")
    public ResponseEntity<ApiResponse<List<ReservationDto.TimeSlotResponseDto>>> getRevTimeInfo(@PathVariable String date, @PathVariable int tableId, Authentication authentication){
        log.info("시간 가져오기 시작");
        List<ReservationDto.TimeSlotResponseDto> timeSlotResponseDto = reservationService.getAvailableTimeSlots(date, tableId);
        log.info(timeSlotResponseDto.toString());
        log.info("apiresponse: "+ApiResponse.createSuccess(timeSlotResponseDto, CustomResponseCode.SUCCESS));
        return ResponseEntity.ok().body(ApiResponse.createSuccess(timeSlotResponseDto, CustomResponseCode.SUCCESS));
    }

    // 예약할 카페 이름&테이블 조회
//    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/cafe/{cafeId}")
    public ResponseEntity<ApiResponse<ReservationDto.RevCafeInfoResponseDto>> getRevCafeInfo(@PathVariable int cafeId, Authentication authentication){
        log.info("예약할 카페 정보 조회 시작");
        ReservationDto.RevCafeInfoResponseDto revCafeInfoResponseDto = reservationService.getRevCafeInfo(cafeId);
        log.info(revCafeInfoResponseDto.toString());
        log.info("apiresponse: "+ApiResponse.createSuccess(revCafeInfoResponseDto, CustomResponseCode.SUCCESS));
        return ResponseEntity.ok().body(ApiResponse.createSuccess(revCafeInfoResponseDto, CustomResponseCode.SUCCESS));
    }

    @GetMapping("/list/finish")
    public ResponseEntity<ApiResponse<String>> readUserFinishReservation(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok().body(ApiResponse.createSuccessWithNoContent(CustomResponseCode.SUCCESS));
    }
    @GetMapping("/now/{userId}")
    public ResponseEntity<ApiResponse<String>> readUserReservationStatus(@PathVariable int userId,Authentication authentication){
        log.info("예약 현황 상태 조회 시작");
        return ResponseEntity.ok().body(ApiResponse.createSuccessWithNoContent(CustomResponseCode.SUCCESS));
    }
}
