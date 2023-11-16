package com.example.demo.reservation.reservation.dto;

import com.example.demo.cafeTable.dto.CafeTableDto;
import com.example.demo.cafeTable.entity.CafeTable;
import com.example.demo.reservation.reservation.entity.Reservation;
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
    @Builder
    public static class RevCafeInfoResponseDto{
        private String cafeName;
        private Map<String, List<CafeTableDto.CafeTableInfoResponseDto>> tableInfo;

        public RevCafeInfoResponseDto(String cafeName, Map<String, List<CafeTableDto.CafeTableInfoResponseDto>> tableInfo) {
            this.cafeName = cafeName;
            this.tableInfo = tableInfo;
        }
    }

    @Data
    @NoArgsConstructor
    @Builder
    public static class TimeSlotResponseDto{
        private String reserveStart;
        private String reserveEnd;
        private String available;

        public TimeSlotResponseDto(String reserveStart, String reserveEnd, String available) {
            this.reserveStart = reserveStart;
            this.reserveEnd = reserveEnd;
            this.available = available;
        }
    }

    @Data
    @NoArgsConstructor
    @Builder
    public static class DateReservationResponseDto{
        private List<Integer> reservationIds;
        private String userRealName;
        private String tableNumber;
        private String tableType;
        private int personCnt;
        private String reserveStart;
        private String reserveEnd;

        public DateReservationResponseDto(List<Integer> reservationIds, String userRealName, String tableNumber,
                                          String tableType, int personCnt, String reserveStart, String reserveEnd) {
            this.reservationIds = reservationIds;
            this.userRealName = userRealName;
            this.tableNumber = tableNumber;
            this.tableType = tableType;
            this.personCnt = personCnt;
            this.reserveStart = reserveStart;
            this.reserveEnd = reserveEnd;
        }
    }


}
