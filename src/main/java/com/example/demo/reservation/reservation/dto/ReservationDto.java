package com.example.demo.reservation.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public class ReservationDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserReservationReqDto{
        private int tableId;
        private int cafeId;
        private int userId;
        private String reserveStart;
        private String reserveEnd;
        private int personCnt;
        private String reserveDate;
    }

    @Data
    @NoArgsConstructor
    @Builder
    public static class UserReservationResDto{
        private String reservationDate;
        private String cafeName;

        public UserReservationResDto(String reservationDate, String cafeName) {
            this.reservationDate = reservationDate;
            this.cafeName = cafeName;
        }
    }

}
