package com.example.demo.cafeFeature.mapper;

import com.example.demo.cafe.dto.CafeDto;
import com.example.demo.cafeFeature.entity.CafeFeature;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CafeFeatureMapper {
    void insertCafeFeatures(int cafeId, int featureId);

    List<CafeFeature> readCafeFeatures(int cafeId);
}
