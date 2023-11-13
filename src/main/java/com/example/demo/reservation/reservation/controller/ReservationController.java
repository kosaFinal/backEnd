package com.example.demo.reservation.reservation.controller;

import com.example.demo.cafe.service.CafeService;
import com.example.demo.cafeTable.dto.CafeTableDto;
import com.example.demo.cafeTable.service.CafeTableService;
import com.example.demo.constant.dto.ApiResponse;
import com.example.demo.constant.enums.CustomResponseCode;
import com.example.demo.constant.exception.GeneralException;
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
    private final CafeService cafeService;
    private final CafeTableService cafeTableService;

    // 예약 생성
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<ReservationDto.UserReservationResponseDto>> createReservation(@RequestBody ReservationDto.UserReservationRequestDto userReservationRequestDto) {
        log.info("예약 생성 요청 받음");
        ReservationDto.UserReservationResponseDto userReservationResponseDto = reservationService.createReservation(userReservationRequestDto);
        log.info(String.valueOf(userReservationResponseDto));
        log.info("apiresponse: "+ApiResponse.createSuccess(userReservationResponseDto, CustomResponseCode.SUCCESS));

        return ResponseEntity.ok().body(ApiResponse.createSuccess(userReservationResponseDto, CustomResponseCode.SUCCESS));
    }

    // 예약할 카페 정보 조회
    @GetMapping("/cafe/{cafeId}")
    public ResponseEntity<ApiResponse<ReservationDto.RevCafeInfoResponseDto>> getRevCafeInfo(@PathVariable int cafeId){
        String cafeName = cafeService.findCafeNameByCafeId(cafeId);

        if (cafeName == null) {
            throw new GeneralException(CustomResponseCode.CAFE_NOT_FOUND);
        }

        Map<String, List<CafeTableDto.CafeTableInfoResponseDto>> tableInfo = cafeTableService.getTableInfo(cafeId);

        ReservationDto.RevCafeInfoResponseDto revCafeInfoResDto = ReservationDto.RevCafeInfoResponseDto.builder()
                .cafeName(cafeName)
                .tableInfo(tableInfo)
                .build();

        return ResponseEntity.ok().body(ApiResponse.createSuccess(revCafeInfoResDto, CustomResponseCode.SUCCESS));
    }

}
