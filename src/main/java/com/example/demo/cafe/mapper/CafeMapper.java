package com.example.demo.cafe.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CafeMapper {
    String findCafeNameByCafeId(int cafeId);
}
