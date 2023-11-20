package com.example.demo.search.service.Impl;

import com.example.demo.cafe.entity.Cafe;
import com.example.demo.search.dto.SearchDto;
import com.example.demo.search.dto.SearchRequestDto;
import com.example.demo.search.mapper.SearchMapper;
import com.example.demo.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchServiceImpl implements SearchService {
    private final SearchMapper searchMapper;

    /**
     *private String cafeType;
     *         private String studyEnable;
     *         private String people;
     *         private String ing;
     *         private ArrayList<String> features;
     *         private String startTime;
     *         private String endTime;
     *         private String userStudy;
     *         private String preferSeat;
     */
    @Override
    public SearchDto.SearchResponseDto search(SearchDto.SearchRequestDto searchRequestDto) {
        log.info("searchRequestDto: "+ searchRequestDto);
        List<Cafe> cafeList = searchMapper.search(searchRequestDto);

        return new SearchDto.SearchResponseDto(cafeList);
    }

    @Override
    public List<String> searchWord(String word) {

        return searchMapper.searchWord(word);
    }

    @Override
    public SearchDto.SearchResponseDto searchByMyLocation(Double longtitude, Double latitude) {
        List<Cafe> cafes = searchMapper.searchByMyLocation(longtitude,latitude);
        return new SearchDto.SearchResponseDto(cafes);
    }
}
