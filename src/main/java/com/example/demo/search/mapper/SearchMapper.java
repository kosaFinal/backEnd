package com.example.demo.search.mapper;

import com.example.demo.cafe.entity.Cafe;
import com.example.demo.search.dto.SearchDto;
import com.example.demo.search.dto.SearchRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface SearchMapper {
    List<Cafe> search(SearchDto.SearchRequestDto searchRequestDto);
    List<Cafe> searchWord(String word);

    List<Cafe> searchByMyLocation(Double longtitude, Double latitude);

}
