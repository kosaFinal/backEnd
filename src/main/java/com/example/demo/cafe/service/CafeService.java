package com.example.demo.cafe.service;

import com.example.demo.cafe.dto.CafeDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CafeService {
    String findCafeNameByCafeId(int cafeId);

    CafeDto.CafeRegisterResponseDto registerCafe(CafeDto.CafeRegisterRequestDto requestDto);
}