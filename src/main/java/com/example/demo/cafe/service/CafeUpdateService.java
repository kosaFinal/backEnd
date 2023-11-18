package com.example.demo.cafe.service;

import com.example.demo.cafe.dto.CafeUpdateDto;

public interface CafeUpdateService {
    CafeUpdateDto.CafeTelResponseDto cafeTelUpdate(CafeUpdateDto.CafeTelRequestDto cafeTel, String userName);

    CafeUpdateDto.CafeLocationResponseDto cafeAddressUpdate(CafeUpdateDto.CafeLocationRequestDto cafeAddress, String userName);
    CafeUpdateDto.CafeTimeResponseDto cafeTimeUpdate(CafeUpdateDto.CafeTimeRequestDto cafeTime, String userName);

    CafeUpdateDto.CafeStudyResponseDto cafeStudyUpdate(CafeUpdateDto.CafeStudyRequestDto cafeStudy, String userName);

}
