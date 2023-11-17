package com.example.demo.search.dto;

import com.example.demo.cafe.dto.CafeDto;
import com.example.demo.cafe.entity.Cafe;
import com.example.demo.cafeFeature.dto.CafeFeatureDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchDto {

    @Getter
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchRequestDto{
        private String cafeType;
        private String studyEnable;
        private String people;
        private String ing;
        @Getter
        private List<String> features;
        private String startTime;
        private String endTime;
        private String userStudy;
        private String preferSeat;

    }

    @Builder
    @AllArgsConstructor
    public static class SearchResponseDto{
        private List<CafeDto.CafeSearchResponseDto> searchCafes;
        private List<CafeDto.CafeLocationResponseDto> locations;

        public SearchResponseDto(List<Cafe> cafes){
            this.searchCafes = cafes.stream().map(CafeDto.CafeSearchResponseDto::new)
                    .collect(Collectors.toList());
            this.locations = cafes.stream()
                    .map(CafeDto.CafeLocationResponseDto::new)
                    .collect(Collectors.toList());
        }

    }
}
