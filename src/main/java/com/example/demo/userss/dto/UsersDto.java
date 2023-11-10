package com.example.demo.userss.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class UsersDto {
    @Data
    @AllArgsConstructor
    @Getter

    public static class UserLoginRequestDto{
        private String userName;
        private String password;
    }

    @Builder
    public static class UserLoginResponseDto{
        private String userName;
        private String accessToken;
    }
}
