package com.example.demo.cafe.entity;

import com.example.demo.userss.entity.Users;
import lombok.Getter;

import java.util.Date;

@Getter
public class Cafe {

    private int cafeId;
    private String cafeName;
    private String cafeType;
    private String startTime;
    private String endTime;
    private String address;
    private String longtitude; //경도
    private String latitude; //위도
    private String cafeTel;
    private String study;
    private Users user;
    private byte[] cafeRepImg;

}
