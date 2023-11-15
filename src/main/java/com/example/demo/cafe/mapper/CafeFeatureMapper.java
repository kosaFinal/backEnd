package com.example.demo.cafe.mapper;

import com.example.demo.cafe.dto.CafeDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CafeFeatureMapper {
    void insertCafeFeatures(int cafeId, int featureId);

}
