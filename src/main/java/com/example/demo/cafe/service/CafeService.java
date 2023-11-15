package com.example.demo.cafe.service;

import com.example.demo.cafe.dto.CafeDto;

public interface CafeService {

    int findUserIdByCafeId(int cafeId);

    String findCafeNameByCafeId(int cafeId);

    void registerCafe(CafeDto.CafeRegisterRequestDto requestDto, String userName);
}

