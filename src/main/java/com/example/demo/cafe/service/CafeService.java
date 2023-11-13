package com.example.demo.cafe.service;

import com.example.demo.cafe.dto.CafeDto;

public interface CafeService {
    String findCafeNameByCafeId(int cafeId);
    CafeDto.CafeRegisterResponseDto registerCafe(CafeDto.CafeRegisterRequestDto requestDto);
}
