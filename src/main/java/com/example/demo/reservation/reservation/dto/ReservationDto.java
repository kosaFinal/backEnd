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
    @NoArgsConstructor
    public static class UserReservationRequestDto{
        private int tableId;
        private List<Reservation.TimeSlot> reserveTime;
        private int personCnt;
        private String reserveDate;
    }

    @Data
    @Builder
    public static class RevCafeInfoResponseDto{
        private String cafeName;
        private Map<String, List<CafeTableDto.CafeTableInfoResponseDto>> tableInfo;

    }

    @Data
    @Builder
    public static class TimeSlotResponseDto{
        private String reserveStart;
        private String reserveEnd;
        private String available;

    }

    @Data
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
    public static class CofirmReservationRequestDto{
        private List<Integer> reservationIds;

    }

    @Data
    public static class CancleReservationRequestDto{
        private List<Integer> reservationIds;
        private String cancleReasonId;

    }

}
