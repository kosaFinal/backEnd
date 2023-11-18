package com.example.demo.cafe.service;

import com.example.demo.cafe.dto.CafeUpdateDto;

public interface CafeUpdateService {
    CafeUpdateDto.CafeTelResponseDto cafeUpdate(CafeUpdateDto.CafeTelRequestDto cafeTel, String userName);

}
