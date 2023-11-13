package com.example.demo.reservation.reservation.entity;

import com.example.demo.constant.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation extends BaseEntity {

    private int reservationId;
    private int tableId;
    private int cafeId;
    private int userId;
    private String reserveStart;
    private String reserveEnd;
    private int personCnt;
    private String status;
    private String reserveDate;
    private String cancleReasonId;

}
