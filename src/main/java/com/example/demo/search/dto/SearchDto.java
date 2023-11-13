package com.example.demo.search.dto;

import com.example.demo.cafe.dto.CafeDto;
import com.example.demo.cafe.entity.Cafe;
import com.example.demo.cafeFeature.dto.CafeFeatureDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchDto {

    @Getter
    public static class SearchRequestDto{
        private String cafeType;
        private String studyEnable;
        private String people;
        private String ing;
        private ArrayList<String> features;
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

        public SearchResponseDto(ArrayList<Cafe> cafes){
            this.searchCafes = cafes.stream().map(CafeDto.CafeSearchResponseDto::new)
                    .collect(Collectors.toList());
            this.locations = cafes.stream()
                    .map(CafeDto.CafeLocationResponseDto::new)
                    .collect(Collectors.toList());
        }
    }
}
