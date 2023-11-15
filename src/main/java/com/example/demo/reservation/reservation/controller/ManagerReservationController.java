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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/reservation")
@Slf4j
public class ManagerReservationController {

    private final ReservationService reservationService;

    @GetMapping("/read/date/{date}")
    public ResponseEntity<ApiResponse<List<ReservationDto.DateReservationResponseDto>>> getRevCafeInfo(@PathVariable String date, Authentication authentication){
        log.info("날짜별 조회 시작");

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();

        List<ReservationDto.DateReservationResponseDto> responseDto = reservationService.getDateReservation(date, userName);

        return ResponseEntity.ok().body(ApiResponse.createSuccess(responseDto, CustomResponseCode.SUCCESS));
    }
}
