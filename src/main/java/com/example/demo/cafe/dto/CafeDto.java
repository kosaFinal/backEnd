package com.example.demo.cafe.dto;

import com.example.demo.cafe.entity.Cafe;

import com.example.demo.cafeFeature.dto.CafeFeatureDto;
import com.example.demo.cafeTable.dto.CafeTableDto;
import com.example.demo.feature.dto.FeatureDto;
import com.example.demo.reservation.reservation.dto.ReservationDto;
import lombok.*;

import java.util.*;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
/* 매니저 - 카페 조회 */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CafeReadBasicResponseDto{
        private int cafeId;
        private String cafeName;
        private String cafeType;
        private String address;
        private String cafeTel;
        private int userId;
        public CafeReadBasicResponseDto(Cafe cafe) {
            this.cafeId = cafe.getCafeId();
            this.cafeName = cafe.getCafeName();
            this.cafeType = cafe.getCafeType();
            this.address = cafe.getAddress();
            this.cafeTel = cafe.getCafeTel();
            this.userId = cafe.getUserId();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CafeReadDetailResponseDto{
        private int cafeId;
        private String startTime;
        private String endTime;
        private int userId;

        public CafeReadDetailResponseDto(Cafe cafe){
            this.cafeId = cafe.getCafeId();
            this.startTime = cafe.getStartTime();
            this.endTime = cafe.getEndTime();
            this.userId = cafe.getUserId();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CafeDetailsResponseWrapper {
        private CafeDto.CafeReadDetailResponseDto detailResponse;
        private CafeFeatureDto.CafeFeatureResponseDto featureResponse;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CafeReadSettingResponseDto{
        private int cafeId;
        private String study;
        private int userId;
        private String studyImg;
        private String studyImgMine;

        public CafeReadSettingResponseDto(Cafe cafe){
            this.cafeId = cafe.getCafeId();
            this.study = cafe.getStudy();
            this.userId = cafe.getUserId();
            this.studyImg = Base64.getEncoder().encodeToString(cafe.getStudyImg());
            this.studyImgMine = cafe.getStudyImgMine();
        }

    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CafeSettingResponseWrapper {
        private CafeDto.CafeReadSettingResponseDto SettingResponse;
        private ReservationDto.RevCafeInfoResponseDto CafeTableResponse;
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
        private int cafeId;
        private String cafeName;
        private String address;
        private String startTime;
        private String endTime;
        private String cafeTel;
        private List<FeatureDto.FeatureResponseDto> features;
        private String study;

        public CafeSearchDetailResponseDto(Cafe cafe, List<FeatureDto.FeatureResponseDto> features){
            this.cafeName = cafe.getCafeName();
            this.address = cafe.getAddress();
            this.startTime = cafe.getStartTime();
            this.endTime = cafe.getEndTime();
            this.cafeTel = cafe.getCafeTel();
            this.features = features;
            this.study = cafe.getStudy();
        }
    }

}
