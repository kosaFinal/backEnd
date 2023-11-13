package com.example.demo.reservation.reservation.service.impl;

import com.example.demo.cafe.entity.Cafe;
import com.example.demo.cafe.service.CafeService;
import com.example.demo.cafeTable.service.CafeTableService;
import com.example.demo.constant.enums.CustomResponseCode;
import com.example.demo.constant.exception.GeneralException;
import com.example.demo.reservation.reservation.dto.ReservationDto;
import com.example.demo.reservation.reservation.entity.Reservation;
import com.example.demo.reservation.reservation.mapper.ReservationMapper;
import com.example.demo.reservation.reservation.service.ReservationService;
import com.example.demo.userss.entity.Users;
import com.example.demo.userss.mapper.UsersMapper;
import com.example.demo.userss.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    private final ReservationMapper reservationMapper;
    private final CafeService cafeService;
    private final CafeTableService cafeTableService;
    private final UsersService usersService;

    @Override
    public ReservationDto.UserReservationResponseDto createReservation(ReservationDto.UserReservationRequestDto userReservationRequestDto) {
        log.info("서비스 시작");
        log.info(String.valueOf(userReservationRequestDto));

        // 존재하는 카페인지 확인 + res에 담을 cafeName 가져오기
        String cafeName = cafeService.findCafeNameByCafeId(userReservationRequestDto.getCafeId());
        log.info(cafeName);
        if (cafeName == null) {
            throw new GeneralException(CustomResponseCode.CAFE_NOT_FOUND);
        }

        // 존재하는 카페테이블인지 확인
        Boolean resultCafeTable = cafeTableService.checkCafeId(userReservationRequestDto.getTableId());
        if(resultCafeTable == false){
            throw new GeneralException(CustomResponseCode.CAFETABLE_NOT_FOUND);
        }

        // 존재하는 유저인지 확인
        Boolean resultUserId = usersService.checkUserId(userReservationRequestDto.getUserId());
        if(resultUserId == false){
            throw new GeneralException(CustomResponseCode.USER_NOT_FOUND);
        }

        Reservation reservation =  Reservation.builder()
                .tableId(userReservationRequestDto.getTableId())
                .cafeId(userReservationRequestDto.getCafeId())
                .userId(userReservationRequestDto.getUserId())
                .reserveStart(userReservationRequestDto.getReserveStart())
                .reserveEnd(userReservationRequestDto.getReserveEnd())
                .personCnt(userReservationRequestDto.getPersonCnt())
                .status("A")
                .reserveDate(userReservationRequestDto.getReserveDate())
                .build();

        log.info(String.valueOf(reservation));

        reservationMapper.createReservation(reservation);


        return ReservationDto.UserReservationResponseDto.builder()
                .reservationDate(userReservationRequestDto.getReserveDate())
                .cafeName(cafeName)
                .build();
    }
}
