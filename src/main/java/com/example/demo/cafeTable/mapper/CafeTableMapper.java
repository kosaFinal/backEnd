package com.example.demo.cafeTable.mapper;

import com.example.demo.cafeTable.entity.CafeTable;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CafeTableMapper {
    CafeTable checkTableId(int tableId);
}
