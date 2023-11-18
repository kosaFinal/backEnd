package com.example.demo.cafe.mapper;

import com.example.demo.cafe.entity.Cafe;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CafeUpdateMapper {
    int updateCafeTel(String cafeTel, int cafeId);
    int updateCafeAddress(String address, double longtitude, double latitude, int cafeId);

}
