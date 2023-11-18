package com.example.demo.cafe.dto;

import com.example.demo.cafe.entity.Cafe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CafeUpdateDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CafeTelRequestDto{
        private String cafeTel;

        public CafeTelRequestDto(Cafe cafe){
            this.cafeTel = cafe.getCafeTel();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CafeTelResponseDto{
        private int cafeId;
        private String cafeTel;

        public CafeTelResponseDto(Cafe cafe){
            this.cafeId = cafe.getCafeId();
            this.cafeTel = cafe.getCafeTel();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CafeLocationRequestDto{
        private String address;
        private Double longtitude;
        private Double latitude;

        public CafeLocationRequestDto(Cafe cafe){
            this.address = cafe.getAddress();
            this.longtitude = cafe.getLongtitude();
            this.latitude = cafe.getLatitude();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CafeLocationResponseDto{
        private int cafeId;
        private String address;
        private Double longtitude;
        private Double latitude;

        public CafeLocationResponseDto(Cafe cafe){
            this.cafeId = cafe.getCafeId();
            this.address = cafe.getAddress();
            this.longtitude = cafe.getLongtitude();
            this.latitude = cafe.getLatitude();
        }
    }


}
