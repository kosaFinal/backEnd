package com.example.demo.cafe.mapper;

import com.example.demo.cafe.entity.Cafe;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CafeMapper {
    Cafe getOneCafe(int cafeId);
}
