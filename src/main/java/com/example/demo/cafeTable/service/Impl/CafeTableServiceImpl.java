package com.example.demo.cafeTable.service.Impl;

import com.example.demo.cafeTable.dto.CafeTableDto;
import com.example.demo.cafeTable.entity.CafeTable;
import com.example.demo.cafeTable.mapper.CafeTableMapper;
import com.example.demo.cafeTable.service.CafeTableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CafeTableServiceImpl implements CafeTableService {

    private final CafeTableMapper cafeTableMapper;

    @Override
    public Map<String, List<CafeTableDto.CafeTableInfoResponseDto>> getTableInfo(int cafeId) {
        Map<String, List<CafeTableDto.CafeTableInfoResponseDto>> result = new HashMap<>();
        String[] tableTypes = {"O", "T", "F", "M"};

        for(String tableType : tableTypes){
            List<CafeTableDto.CafeTableInfoResponseDto> tableInfoList = cafeTableMapper.getTableInfo(cafeId, tableType);
            result.put(tableType, tableInfoList);
        }

        return result;
    }

}
