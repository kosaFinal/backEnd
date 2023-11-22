package com.example.demo.search.dto;

import com.example.demo.cafe.dto.CafeDto;
import com.example.demo.cafe.dto.PageDto;
import com.example.demo.cafe.entity.Cafe;
import com.example.demo.cafeFeature.dto.CafeFeatureDto;
import lombok.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class SearchDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchRequestDto{
        private String cafeType;
        private String studyEnable;
        private String people; //다인석
        private String proceed;  //운영중
        private List<String> features;  //특성
        private String startTime;
        private String endTime;
        private String preferSeat;
        private String word;
    }

    @Builder
    @Data
    public static class SearchResponseDto{
        private List<CafeDto.CafeSearchResponseDto> searchCafes;
        private List<CafeDto.CafeLocationResponseDto> locations;
        private PageDto pager;

        public SearchResponseDto(List<CafeDto.CafeSearchResponseDto> searchCafes, List<CafeDto.CafeLocationResponseDto> locations, PageDto pager ){
            this.searchCafes = searchCafes;
            this.locations = locations;
            this.pager = pager;
        }

    }
}
