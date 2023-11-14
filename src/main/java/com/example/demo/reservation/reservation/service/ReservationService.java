package com.example.demo.reservation.reservation.service;

import com.example.demo.reservation.reservation.dto.ReservationDto;

import java.util.List;

public interface ReservationService {
    ReservationDto.UserReservationResponseDto createReservation(ReservationDto.UserReservationRequestDto userReservationRequestDto);
    List<ReservationDto.TimeSlotResponseDto> getAvailableTimeSlots(String reserveDate, int tableId);
}
