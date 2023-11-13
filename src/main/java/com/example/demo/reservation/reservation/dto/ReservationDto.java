package com.example.demo.reservation.reservation.dto;

import com.example.demo.cafeTable.dto.CafeTableDto;
import com.example.demo.cafeTable.entity.CafeTable;
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

    @Data
    @NoArgsConstructor
    @Builder
    public static class RevCafeInfoResDto{
        private String cafeName;
        private Map<String, List<CafeTableDto.CafeTableInfo>> tableInfo;

        public RevCafeInfoResDto(String cafeName, Map<String, List<CafeTableDto.CafeTableInfo>> tableInfo) {
            this.cafeName = cafeName;
            this.tableInfo = tableInfo;
        }
    }


}
