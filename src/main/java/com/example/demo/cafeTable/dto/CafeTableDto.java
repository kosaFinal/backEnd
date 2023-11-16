package com.example.demo.cafeTable.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CafeTableDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CafeTableInfoResponseDto {
        private int tableId;
        private String tableNumber;
    }
}
