package com.example.demo.feature.dto;

import com.example.demo.feature.entity.Feature;
import lombok.Data;

public class FeatureDto {

    @Data
    public static class FeatureResponseDto{
        private String featureName;
        public FeatureResponseDto(Feature feature) {
            this.featureName = feature.getFeatureName();
        }
    }
}
