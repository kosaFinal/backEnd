package com.example.demo.search.service.Impl;

import com.example.demo.cafe.dto.PageDto;
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
    public SearchDto.SearchResponseDto search(SearchDto.SearchRequestDto searchRequestDto,int pageNo) {
        log.info("searchRequestDto: "+ searchRequestDto);

        int cafeListSize = searchMapper.search(searchRequestDto);

        PageDto pager = new PageDto(5,5,cafeListSize,pageNo);
        List<Cafe> cafeList = searchMapper.searchByPage(searchRequestDto, pager);

        return new SearchDto.SearchResponseDto(cafeList,pager);
    }

    @Override
    public SearchDto.SearchResponseDto searchWord(String word,int pageNo) {

        int count = searchMapper.searchWordCount(word);
        PageDto pager = new PageDto(5,5,count,pageNo);
        List<Cafe> wordsCafe = searchMapper.searchWord(word,pager);
        return new SearchDto.SearchResponseDto(wordsCafe,pager);
    }

    @Override
    public SearchDto.SearchResponseDto searchByMyLocation(Double longtitude, Double latitude,int pageNo) {
        int count = searchMapper.searchByMyLocationCount(longtitude,latitude);
        PageDto pager = new PageDto(5,5,count,pageNo);
        List<Cafe> cafes = searchMapper.searchByMyLocation(longtitude,latitude,pager);
        return new SearchDto.SearchResponseDto(cafes,pager);
    }
}
