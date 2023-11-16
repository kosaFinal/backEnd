package com.example.demo.reservation.reservation.mapper;

import com.example.demo.reservation.reservation.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    // user
    void createReservation(Reservation reservation);
    List<Reservation> getOneTableRev(String reserveDate, int tableId);

    // manager
    List<Reservation> getOneCafeOneDayRev(String date, int cafeId);
    List<Reservation> getOneCafeBeforeRev(int cafeId);
    List<Reservation> getOneCafeIngRev(int cafeId);
}
