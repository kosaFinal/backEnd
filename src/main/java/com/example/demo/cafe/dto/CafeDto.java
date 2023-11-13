package com.example.demo.cafe.dto;

import com.example.demo.cafe.entity.Cafe;
import lombok.Data;

public class CafeDto {

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
