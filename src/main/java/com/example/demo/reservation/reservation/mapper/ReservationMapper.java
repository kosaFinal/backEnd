package com.example.demo.reservation.reservation.mapper;

import com.example.demo.reservation.reservation.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    void createReservation(Reservation reservation);
    List<Reservation> findReservation(String reserveDate, int tableId);
}
