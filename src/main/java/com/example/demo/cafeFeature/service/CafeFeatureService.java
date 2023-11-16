package com.example.demo.cafeFeature.service;

import com.example.demo.cafe.dto.CafeDto;
import com.example.demo.cafeFeature.dto.CafeFeatureDto;

public interface CafeFeatureService {
    void insertCafeFeatures(CafeFeatureDto.CafeFeatureRequestDto cafeFeatureRequestDto, String userName);
}
