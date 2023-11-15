package com.example.demo.search.service;

import com.example.demo.search.dto.SearchDto;
import org.springframework.stereotype.Service;


public interface SearchService {
    SearchDto.SearchResponseDto search(SearchDto.SearchRequestDto searchRequestDto);
}
