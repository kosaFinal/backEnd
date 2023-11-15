package com.example.demo.search.mapper;

import com.example.demo.cafe.entity.Cafe;
import com.example.demo.search.dto.SearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface SearchMapper {
    ArrayList<Cafe> search(SearchDto.SearchRequestDto searchRequestDto);
    List<String> searchWord(String word);

}
