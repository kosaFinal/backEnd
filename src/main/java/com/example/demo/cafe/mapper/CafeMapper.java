package com.example.demo.cafe.mapper;


import com.example.demo.cafe.dto.CafeDto;
import com.example.demo.cafe.entity.Cafe;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CafeMapper {

    int findUserIdByCafeId(int userId);
  
    String findCafeNameByCafeId(int cafeId);
  
    void insertCafe(CafeDto.CafeRegisterRequestDto requestDto);
  
    Cafe getOneCafe(int cafeId);

    Cafe findCafeBasicByUserId(int userId);

}
