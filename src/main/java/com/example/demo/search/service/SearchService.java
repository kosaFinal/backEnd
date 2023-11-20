package com.example.demo.search.service;

import com.example.demo.search.dto.SearchDto;
import com.example.demo.search.dto.SearchRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface SearchService {
    SearchDto.SearchResponseDto search(SearchDto.SearchRequestDto searchRequestDto);

    List<String> searchWord(String word);
    SearchDto.SearchResponseDto searchByMyLocation(Double longtitude, Double latitude);
}
