package com.example.demo.cafe.dto;

import com.example.demo.cafe.entity.Cafe;

import lombok.*;
import java.util.Date;
import lombok.Builder;
import lombok.Data;


public class CafeDto {

    @Getter
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CafeRegisterRequestDto{
        private int cafeId;
        private String cafeName;
        private String cafeType;
        private String startTime;
        private String endTime;
        private String address;
        private String longtitude;
        private String latitude;
        private String cafeTel;
        private String study;
        private int userId;
        private byte[] cafeRepImg;
        private byte[] studyImg;
        private Date createDate;
        private Date modifyDate;


    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CafeRegisterResponseDto{
        private int cafeId;
        private String cafeName;
        private int userId;
        private byte[] studyImg;
        public CafeRegisterResponseDto(int userId, byte[] studyImg){
            this.userId = userId;
            this.studyImg = studyImg;
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
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CafeFeatureRequestDto{
        private Boolean comfortableSeats;
        private Boolean hasDesserts;
        private Boolean quiet;
        private Boolean noMusic;
        private Boolean sentimental;
        private Boolean hasPowerOutlets;

    }

    @Data
    public static class CafeLocationResponseDto{
        private Double longtitude;
        private Double latitude;
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

    @Data
    public static class CafeReadBasicResponseDto{
        private int cafeId;
        private String cafeName;
        private String cafeType;
        private String address;
        private String cafeTel;
        private int userId;
    }

    @Data
    public static class CafeReadDetailResponseDto{
        private int cafeId;
        private String startTime;
        private String endTime;
        private int userId;
    }

    @Data
    public static class CafeReadSettingResponseDto{
        private int cafeId;
        private String study;
        private int userId;
        private byte[] studyImg;

    }

    @Builder
    @Data
    public static class CafeTimeResponseDto{
        private String startTime;
        private String endTime;

        public CafeTimeResponseDto(String startTime, String endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

}
