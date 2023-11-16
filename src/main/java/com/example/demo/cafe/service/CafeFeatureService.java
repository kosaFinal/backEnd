package com.example.demo.cafe.service;

import com.example.demo.cafe.dto.CafeDto;

public interface CafeFeatureService {
    void insertCafeFeatures(CafeDto.CafeFeatureRequestDto cafeFeatureRequestDto,  String userName);
}
