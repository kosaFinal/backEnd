package com.example.demo.reservation.reservation.service;

import com.example.demo.reservation.reservation.dto.ReservationDto;
import org.springframework.security.core.Authentication;


import java.util.List;

public interface ReservationService {

    // user
    Boolean createReservation(ReservationDto.UserReservationRequestDto userReservationRequestDto, String userName);
    List<ReservationDto.TimeSlotResponseDto> getAvailableTimeSlots(String reserveDate, int tableId);
    ReservationDto.RevCafeInfoResponseDto getRevCafeInfo(int cafeId);
    ReservationDto.UserReservationStatusResponseDto reservationStatus(String userName);
    ReservationDto.CancleReasonResponDto cancleReason(int reservationId);

    // manager
    List<ReservationDto.ManagerReservationResponseDto> getDateReservation(String date, String userName);
    List<ReservationDto.ManagerReservationResponseDto> getBeforeReservation(String userName);
    List<ReservationDto.ManagerReservationResponseDto> getIngReservation(String userName);

    Boolean confirmReservation(ReservationDto.CofirmReservationRequestDto requestDto, String userName);
    Boolean cancleReservation(ReservationDto.CancleReservationRequestDto requestDto, String userName);

    List<ReservationDto.UserReadFinishReservResponseDto> finishReservations(String userName);
    List<ReservationDto.UserReadFinishReservResponseDto> proceedReservations(String userName);

}
