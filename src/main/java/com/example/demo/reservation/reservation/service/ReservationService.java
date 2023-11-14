package com.example.demo.reservation.reservation.service;

import com.example.demo.reservation.reservation.dto.ReservationDto;
import org.springframework.security.core.Authentication;


import java.util.List;

public interface ReservationService {
    Boolean createReservation(ReservationDto.UserReservationRequestDto userReservationRequestDto, String userName);
    List<ReservationDto.TimeSlotResponseDto> getAvailableTimeSlots(String reserveDate, int tableId);
}
