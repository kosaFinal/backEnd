package com.example.demo.cafeTable.service.Impl;

import com.example.demo.cafeTable.entity.CafeTable;
import com.example.demo.cafeTable.mapper.CafeTableMapper;
import com.example.demo.cafeTable.service.CafeTableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CafeTableServiceImpl implements CafeTableService {

    private final CafeTableMapper cafeTableMapper;
    @Override
    public Boolean checkCafeId(int tableId) {
        Boolean result = false;
        CafeTable cafeTable = cafeTableMapper.checkTableId(tableId);
        if(cafeTable != null){
            result = true;
        }
        return result;
    }
}
