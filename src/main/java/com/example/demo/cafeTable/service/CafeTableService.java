package com.example.demo.cafeTable.service;

import com.example.demo.cafeTable.dto.CafeTableDto;

import java.util.List;
import java.util.Map;

public interface CafeTableService {
    Boolean checkCafeId(int tableId);
    Map<String, List<CafeTableDto.CafeTableInfo>> getTableInfo(int cafeId);
}
