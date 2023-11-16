package com.example.demo.cafe.entity;

import com.example.demo.userss.entity.Users;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class Cafe {

    private int cafeId;
    private String cafeName;
    private String cafeType;
    private String startTime;
    private String endTime;
    private String address;
    private Double longtitude; //경도
    private Double latitude; //위도
    private String cafeTel;
    private String study;
    private Users user;
    private byte[] cafeRepImg;
    private byte[] studyImg;
    private String cafeRepImgMine;
    private String studyImgMine;



}
