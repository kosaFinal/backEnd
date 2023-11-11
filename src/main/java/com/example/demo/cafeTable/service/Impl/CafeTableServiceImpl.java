package com.example.demo.cafeTable.service.Impl;

import com.example.demo.cafeTable.mapper.CafeTableMapper;
import com.example.demo.cafeTable.service.CafeTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CafeTableServiceImpl implements CafeTableService {

    private final CafeTableMapper cafeTableMapper;

    @Override
    public List<String> getTableNumbers(int cafeId, String tableType) {
        return cafeTableMapper.getTableNumbers(cafeId, tableType);
    }
}
