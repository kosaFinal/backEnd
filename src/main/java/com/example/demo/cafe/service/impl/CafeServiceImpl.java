package com.example.demo.cafe.service.impl;

import com.example.demo.cafe.dto.CafeDto;
import com.example.demo.cafe.mapper.CafeMapper;
import com.example.demo.cafe.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CafeServiceImpl implements CafeService {

    private final CafeMapper cafeMapper;
    @Override
    public String findCafeNameByCafeId(int cafeId) {
        return cafeMapper.findCafeNameByCafeId(cafeId);
    }

    @Override
    public CafeDto.CafeRegisterResponseDto registerCafe(CafeDto.CafeRegisterRequestDto requestDto) {
        cafeMapper.insertCafe(requestDto);
        return CafeDto.CafeRegisterResponseDto.builder()
                .cafeName(requestDto.getCafeName())
                .userId(requestDto.getUserId())
                .build();
    }

}
