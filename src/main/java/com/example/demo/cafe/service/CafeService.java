package com.example.demo.cafe.service;

import com.example.demo.cafe.dto.CafeDto;
import org.springframework.web.multipart.MultipartFile;

public interface CafeService {
    int findUserIdByCafeId(int cafeId);

    String findCafeNameByCafeId(int cafeId);

    void registerCafe(CafeDto.CafeRegisterRequestDto requestDto, String userName, MultipartFile cafeRepImgFile, MultipartFile studyImgFile);
}