package com.example.demo.feature.mapper;

import com.example.demo.feature.entity.Feature;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeatureMapper {
    Feature getFeature(int featureId);
}
