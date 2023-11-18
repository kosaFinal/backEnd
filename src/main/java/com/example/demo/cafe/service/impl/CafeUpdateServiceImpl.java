package com.example.demo.cafe.service.impl;

import com.example.demo.cafe.dto.CafeUpdateDto;
import com.example.demo.cafe.entity.Cafe;
import com.example.demo.cafe.mapper.CafeImgMapper;
import com.example.demo.cafe.mapper.CafeMapper;
import com.example.demo.cafe.mapper.CafeUpdateMapper;
import com.example.demo.cafe.service.CafeUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CafeUpdateServiceImpl implements CafeUpdateService {

    private final CafeUpdateMapper cafeUpdateMapper;
    private final CafeImgMapper cafeImgMapper;
    private final CafeMapper cafeMapper;


    @Override
    public CafeUpdateDto.CafeTelResponseDto cafeUpdate(CafeUpdateDto.CafeTelRequestDto cafeTel, String userName) {
        int cafeId = cafeImgMapper.findCafeIdByUserName(userName);
        cafeUpdateMapper.updateCafeTel(cafeTel.getCafeTel(), cafeId);

        Cafe cafe = cafeMapper.getOneCafe(cafeId);
        return new CafeUpdateDto.CafeTelResponseDto(cafe);
    }
}
