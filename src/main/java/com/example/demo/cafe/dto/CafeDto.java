package com.example.demo.cafe.dto;

import com.example.demo.cafe.entity.Cafe;

import com.example.demo.cafeFeature.dto.CafeFeatureDto;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        private Double longtitude;
        private Double latitude;
        private String cafeTel;
        private String study;
        private int userId;
        private byte[] cafeRepImg;
        private byte[] studyImg;
        private Date createDate;
        private Date modifyDate;
        private String cafeRepImgMine;
        private String studyImgMine;

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
        private  int cafeId;
        private String cafeName;
        private String startTime;
        private String endTime;
        private String address;

        public CafeSearchResponseDto(Cafe cafe){
            this.cafeId = cafe.getCafeId();
            this.cafeName = cafe.getCafeName();
            this.startTime = cafe.getStartTime();
            this.endTime = cafe.getEndTime();
            this.address = cafe.getAddress();
        }
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
        private String studyImgMine;

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

    @Getter
    public static class CafeSearchDetailResponseDto{
        private String cafeName;
        private String address;
        private String startTime;
        private String endTime;
        private String cafeTel;
        private List<CafeFeatureDto.MapSelectFeatureRequestDto> features = new ArrayList<>();
        private String study;

        public CafeSearchDetailResponseDto(Cafe cafe){
            this.cafeName = cafe.getCafeName();
            this.address = cafe.getAddress();
            this.startTime = cafe.getStartTime();
            this.endTime = cafe.getEndTime();
            this.cafeTel = cafe.getCafeTel();
            this.study = cafe.getStudy();
        }
    }

}
