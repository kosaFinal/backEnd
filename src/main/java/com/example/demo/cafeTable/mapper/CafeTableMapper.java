package com.example.demo.cafeTable.mapper;

import com.example.demo.cafeTable.dto.CafeTableDto;
import com.example.demo.cafeTable.entity.CafeTable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CafeTableMapper {
    CafeTable checkTableId(int tableId);
    List<CafeTableDto.CafeTableInfo> getTableInfo(int cafeId, String tableType);
}
