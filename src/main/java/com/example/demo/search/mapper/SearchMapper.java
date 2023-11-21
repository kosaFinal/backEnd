package com.example.demo.search.mapper;

import com.example.demo.cafe.dto.PageDto;
import com.example.demo.cafe.entity.Cafe;
import com.example.demo.search.dto.SearchDto;
import com.example.demo.search.dto.SearchRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface SearchMapper {
    int search(SearchDto.SearchRequestDto searchRequestDto);
    int searchWordCount(String word);
    int searchByMyLocationCount(Double longtitude, Double latitude);
    List<Cafe> searchByPage(@Param("searchRequestDto") SearchDto.SearchRequestDto searchRequestDto,@Param("pager") PageDto pager);
    List<Cafe> searchWord(String word,@Param("pager") PageDto pager);

    List<Cafe> searchByMyLocation(Double longtitude, Double latitude,@Param("pager") PageDto pager);

}
