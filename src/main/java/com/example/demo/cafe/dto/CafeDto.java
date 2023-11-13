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

    @Data
    public static class CafeSearchResponseDto{
        private String cafeName;
        private String startTime;
        private String endTime;
        private String address;

        public CafeSearchResponseDto(Cafe cafe){
            this.cafeName = cafe.getCafeName();
            this.startTime = cafe.getStartTime();
            this.endTime = cafe.getEndTime();
            this.address = cafe.getAddress();
        }
    }

    @Data
    public static class CafeLocationResponseDto{
        private String longtitude;
        private String latitude;
        private String cafeName;
        private byte[] cafeReqImg;
        private String cafeTel;

        public CafeLocationResponseDto(Cafe cafe){
            this.longtitude = cafe.getLongtitude();
            this.latitude = cafe.getLatitude();
            this.cafeName = cafe.getCafeName();
            this.cafeReqImg = cafe.getCafeRepImg();
            this.cafeTel = cafe.getCafeTel();
        }
    }

}
