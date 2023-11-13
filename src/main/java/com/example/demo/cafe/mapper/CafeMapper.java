package com.example.demo.cafe.mapper;

import com.example.demo.cafe.dto.CafeDto;
import com.example.demo.reservation.reservation.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CafeMapper {
    String findCafeNameByCafeId(int cafeId);
    void insertCafe(CafeDto.CafeRegisterRequestDto requestDto);
}
