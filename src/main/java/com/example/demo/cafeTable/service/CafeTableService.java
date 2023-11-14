package com.example.demo.cafeTable.service;

import com.example.demo.cafeTable.dto.CafeTableDto;

import java.util.List;
import java.util.Map;

public interface CafeTableService {
    Map<String, List<CafeTableDto.CafeTableInfoResponseDto>> getTableInfo(int cafeId);
}
