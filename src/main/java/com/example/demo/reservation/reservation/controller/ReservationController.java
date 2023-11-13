package com.example.demo.reservation.reservation.controller;

import com.example.demo.cafeTable.service.CafeTableService;
import com.example.demo.constant.dto.ApiResponse;
import com.example.demo.constant.enums.CustomResponseCode;
import com.example.demo.reservation.reservation.dto.ReservationDto;
import com.example.demo.reservation.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/reservation")
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;

    // 예약 생성
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<ReservationDto.UserReservationResDto>> createReservation(@RequestBody ReservationDto.UserReservationReqDto userReservationReqDto) {
        log.info("예약 생성 요청 받음");
        ReservationDto.UserReservationResDto userReservationResDto = reservationService.createReservation(userReservationReqDto);
        log.info(String.valueOf(userReservationResDto));
        log.info("apiresponse: "+ApiResponse.createSuccess(userReservationResDto, CustomResponseCode.SUCCESS));

        return ResponseEntity.ok().body(ApiResponse.createSuccess(userReservationResDto, CustomResponseCode.SUCCESS));
    }

}
