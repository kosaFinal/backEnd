package com.example.demo.cafeTable.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CafeTableMapper {

    List<String> getTableNumbers(int cafeId, String tableType);
}
