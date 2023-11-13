package com.example.demo.constant.enums;

import lombok.Getter;

@Getter
public enum CustomResponseCode {

    //성공(1000)
    SUCCESS(true,1000,"요청에 성공했습니다."),

    //bad request(2000)
    BAD_JWT(false, 2000, "JWT 토큰이 잘못되었습니다."),
    NO_TOKEN(false,2001,"JWT 토큰이 포함되어있지 않습니다."),
    AUTHENTICATION_FAILED(false,2002,"정상적인 JWT가 아닙니다"),

    //unauthorized(권한 없을 때)(3000)
    EXPIRED_JWT(false, 3000, "만료된 토큰입니다."),
    NO_AUTHENTICATION(false,3001,"권한이 없는 유저입니다."),
    NOT_EQUALS_PASSWORD(false,3002,"비밀번호가 틀렸습니다."),

    //not found(4000)
    USER_NOT_FOUND(false,4000,"해당 유저를  찾을 수 없습니다."),
    CAFE_NOT_FOUND(false,4001,"해당 카페를 찾을 수 없습니다."),
    RESERVATION_NOT_FOUND(false,4002,"해당 예약을 찾을 수 없습니다."),
    CAFETABLE_NOT_FOUND(false,4003,"해당 카페테이블을 찾을 수 없습니다.");

    //internal server error(기타 에러)(5000)

    private final boolean isSuccess;
    private final int code;
    private final String message;


    CustomResponseCode(boolean isSuccess,int code, String message) {
        this.code = code;
        this.message = message;
        this.isSuccess = isSuccess;
    }
}
