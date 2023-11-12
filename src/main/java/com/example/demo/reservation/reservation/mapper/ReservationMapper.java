package com.example.demo.reservation.reservation.mapper;

import com.example.demo.reservation.reservation.dto.ReservationDto;
import com.example.demo.reservation.reservation.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {
    void createReservation(ReservationDto.UserReservationReqDto userReservationReqDto);
}
