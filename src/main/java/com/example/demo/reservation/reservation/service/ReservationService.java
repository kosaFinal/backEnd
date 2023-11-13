package com.example.demo.reservation.reservation.service;

import com.example.demo.reservation.reservation.dto.ReservationDto;

public interface ReservationService {

    ReservationDto.UserReservationResDto createReservation(ReservationDto.UserReservationReqDto userReservationReqDto);
}
