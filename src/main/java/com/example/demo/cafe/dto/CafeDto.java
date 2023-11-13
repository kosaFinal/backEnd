package com.example.demo.cafe.dto;

import lombok.*;

import java.util.Date;

public class CafeDto {

    @Getter
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CafeRegisterRequestDto{
        String cafeName;
        String cafeType;
        String startTime;
        String endTime;
        String address;
        String longtitude;
        String latitude;
        String cafeTel;
        String study;
        int userId;
        String cafeRepImg;
        String studyImg;
        Date createDate;
        Date modifyDate;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CafeRegisterResponseDto{
        String cafeName;
        int userId;
        public CafeRegisterResponseDto(int userId){
            this.userId = userId;
        }
    }


}
