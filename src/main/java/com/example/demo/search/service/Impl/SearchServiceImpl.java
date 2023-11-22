package com.example.demo.search.service.Impl;

import com.example.demo.cafe.dto.CafeDto;
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
import java.util.Base64;
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
        log.info(cafeList.toString());
        //임시//
        List<CafeDto.CafeSearchResponseDto> newList = new ArrayList<>();
        List<CafeDto.CafeLocationResponseDto> locations = new ArrayList<>();
        for(Cafe c : cafeList){
            CafeDto.CafeSearchResponseDto cafedto = null;
            CafeDto.CafeLocationResponseDto location = null;
            if(searchMapper.findRepImg(c.getCafeId()).getCafeRepImg() == null){
                cafedto = new CafeDto.CafeSearchResponseDto(c, null);
                location = new CafeDto.CafeLocationResponseDto(c,null);
            }
            else {
                c.setCafeRepImg(searchMapper.findRepImg(c.getCafeId()).getCafeRepImg());
                cafedto = new CafeDto.CafeSearchResponseDto(c, Base64.getEncoder().encodeToString(c.getCafeRepImg()));
                location = new CafeDto.CafeLocationResponseDto(c,Base64.getEncoder().encodeToString(c.getCafeRepImg()));
            }
            newList.add(cafedto);
            locations.add(location);
        }
//        log.info(cafeList.toString());
        log.info(newList.toString());

        return new SearchDto.SearchResponseDto(newList,locations,pager);
    }

    @Override
    public SearchDto.SearchResponseDto searchWord(String word,int pageNo) {

        int count = searchMapper.searchWordCount(word);
        PageDto pager = new PageDto(5,5,count,pageNo);
        List<Cafe> wordsCafe = searchMapper.searchWord(word,pager);
        //임시//
        List<CafeDto.CafeSearchResponseDto> newList = new ArrayList<>();
        List<CafeDto.CafeLocationResponseDto> locations = new ArrayList<>();
        for(Cafe c : wordsCafe){
            CafeDto.CafeSearchResponseDto cafedto = null;
            CafeDto.CafeLocationResponseDto location = null;
            if(searchMapper.findRepImg(c.getCafeId()).getCafeRepImg() == null){
                cafedto = new CafeDto.CafeSearchResponseDto(c, null);
                location = new CafeDto.CafeLocationResponseDto(c,null);
            }
            else {
                c.setCafeRepImg(searchMapper.findRepImg(c.getCafeId()).getCafeRepImg());
                cafedto = new CafeDto.CafeSearchResponseDto(c, Base64.getEncoder().encodeToString(c.getCafeRepImg()));
                location = new CafeDto.CafeLocationResponseDto(c,Base64.getEncoder().encodeToString(c.getCafeRepImg()));
            }
            newList.add(cafedto);
            locations.add(location);
        }
        return new SearchDto.SearchResponseDto(newList,locations,pager);
    }

    @Override
    public SearchDto.SearchResponseDto searchByMyLocation(Double longtitude, Double latitude,int pageNo) {
        int count = searchMapper.searchByMyLocationCount(longtitude, latitude);
        PageDto pager = new PageDto(5, 5, count, pageNo);
        List<Cafe> cafes = searchMapper.searchByMyLocation(longtitude, latitude, pager);
        //임시//
        List<CafeDto.CafeSearchResponseDto> newList = new ArrayList<>();
        List<CafeDto.CafeLocationResponseDto> locations = new ArrayList<>();
        for (Cafe c : cafes) {
            CafeDto.CafeSearchResponseDto cafedto = null;
            CafeDto.CafeLocationResponseDto location = null;
            if (searchMapper.findRepImg(c.getCafeId()).getCafeRepImg() == null) {
                cafedto = new CafeDto.CafeSearchResponseDto(c, null);
                location = new CafeDto.CafeLocationResponseDto(c, null);
            } else {
                c.setCafeRepImg(searchMapper.findRepImg(c.getCafeId()).getCafeRepImg());
                cafedto = new CafeDto.CafeSearchResponseDto(c, Base64.getEncoder().encodeToString(c.getCafeRepImg()));
                location = new CafeDto.CafeLocationResponseDto(c, Base64.getEncoder().encodeToString(c.getCafeRepImg()));
            }
            newList.add(cafedto);
            locations.add(location);
        }
        return new SearchDto.SearchResponseDto(newList, locations, pager);
    }

}
