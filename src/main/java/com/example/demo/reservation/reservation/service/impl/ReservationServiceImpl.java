package com.example.demo.reservation.reservation.service.impl;

import com.example.demo.cafe.service.CafeService;
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
    private final CafeService cafeService;

    @Override
    public ReservationDto.UserReservationResDto createReservation(ReservationDto.UserReservationReqDto userReservationReqDto) {
        log.info("서비스 시작");
        log.info(String.valueOf(userReservationReqDto));

        String cafeName = cafeService.findCafeNameByCafeId(userReservationReqDto.getCafeId());
        log.info(cafeName);

        if (cafeName == null) {
            throw new GeneralException(CustomResponseCode.CAFE_NOT_FOUND);
        }

        // 시간 포맷 변경
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date reserveStart;
        Date reserveEnd;
        Date reserveDate;

        try {
            reserveStart = timeFormat.parse(userReservationReqDto.getReserveStart());
            reserveEnd = timeFormat.parse(userReservationReqDto.getReserveEnd());
            reserveDate = dateFormat.parse(userReservationReqDto.getReserveDate());
        } catch (ParseException e) {
            throw new GeneralException(CustomResponseCode.INVALID_TIME_FORMAT);
        }

        log.info(String.valueOf(reserveStart));
        log.info(String.valueOf(reserveEnd));
        log.info(String.valueOf(reserveDate));

        Reservation reservation =  Reservation.builder()
                .tableId(userReservationReqDto.getTableId())
                .cafeId(userReservationReqDto.getCafeId())
                .userId(userReservationReqDto.getUserId())
                .reserveStart(reserveStart)
                .reserveEnd(reserveEnd)
                .personCnt(userReservationReqDto.getPersonCnt())
                .status("A")
                .reserveDate(reserveDate)
                .build();

        log.info(String.valueOf(reservation));

        reservationMapper.createReservation(reservation);

        return ReservationDto.UserReservationResDto.builder()
                .reservationDate(userReservationReqDto.getReserveDate())
                .cafeName(cafeName)
                .build();
    }
}
