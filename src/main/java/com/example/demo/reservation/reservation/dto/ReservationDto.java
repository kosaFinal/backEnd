package com.example.demo.reservation.reservation.dto;

import com.example.demo.cafeTable.dto.CafeTableDto;
import com.example.demo.reservation.reservation.entity.Reservation;
import com.example.demo.userss.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ReservationDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserReservationRequestDto{
        private int tableId;
        private List<Reservation.TimeSlot> reserveTime;
        private int personCnt;
        private String reserveDate;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RevCafeInfoResponseDto{
        private String cafeName;
        private Map<String, List<CafeTableDto.CafeTableInfoResponseDto>> tableInfo;
        private String studyImg;
        private String studyImgMine;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TimeSlotResponseDto{
        private String reserveStart;
        private String reserveEnd;
        private String available;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ManagerReservationResponseDto{
        private List<Integer> reservationIds;
        private String userRealName;
        private String tableNumber;
        private String tableType;
        private int personCnt;
        private String reserveStart;
        private String reserveEnd;
        private String reserveDate;

    }

    @Data
    public static class ConfAndFinReservationRequestDto{
        private List<Integer> reservationIds;
    }

    @Data
    public static class UserReadFinishReservResponseDto {
        private List<Integer> reservationIds;
        private String tableNumber;
        private String reserveStart;
        private String reserveEnd;
        private String reserveDate;
        private String state;

        public UserReadFinishReservResponseDto(Reservation reservation, List<Integer> reservationIds, String tableNumber) {
            this.reservationIds = reservationIds;
            this.tableNumber = tableNumber;
            this.reserveStart = reservation.getReserveStart();
            this.reserveEnd = reservation.getReserveEnd();
            this.reserveDate = reservation.getReserveDate();
            this.state = reservation.getStatus();
        }
    }

    @Data
    public static class CancleReservationRequestDto {
        private List<Integer> reservationIds;
        private String cancleReasonId;
    }

    @Data
    @NoArgsConstructor
    @Builder
    public static class  UserReservationStatusResponseDto{
        private  String userName;
        private String status;
        private int reservationId;

        public UserReservationStatusResponseDto(String userName, String status, int reservationId) {
            this.userName = userName;
            this.status = status;
            this.reservationId = reservationId;
        }

    }
    @Data
    @NoArgsConstructor
    @Builder
    public static class CancleReasonResponDto {
        private int reservationId;
        private String cancleContent;
        public CancleReasonResponDto(int reservationId, String cancleContent) {
            this.reservationId = reservationId;
            this.cancleContent = cancleContent;
        }
    }

}
