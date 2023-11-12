package com.example.demo.reservation.reservation.service.impl;

import com.example.demo.cafe.mapper.CafeMapper;
import com.example.demo.constant.enums.CustomResponseCode;
import com.example.demo.constant.exception.GeneralException;
import com.example.demo.reservation.reservation.dto.ReservationDto;
import com.example.demo.reservation.reservation.entity.Reservation;
import com.example.demo.reservation.reservation.mapper.ReservationMapper;
import com.example.demo.reservation.reservation.service.ReservationService;
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
    private final CafeMapper cafeMapper;

    @Override
    public ReservationDto.UserReservationResDto createReservation(ReservationDto.UserReservationReqDto userReservationReqDto) {
        log.info("서비스 시작");
        log.info(String.valueOf(userReservationReqDto));
        String cafeName = cafeMapper.findCafeNameByCafeId(userReservationReqDto.getCafeId());
        log.info(cafeName);
//        if (cafeName == null) {
//            throw new GeneralException(CustomResponseCode.CAFE_NOT_FOUND);
//        }

        reservationMapper.createReservation(userReservationReqDto);

        return ReservationDto.UserReservationResDto.builder()
                .reservationDate(userReservationReqDto.getReserveDate())
                .cafeName(cafeName)
                .build();
    }
}
