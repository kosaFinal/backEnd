package com.example.demo.search.controller;

import com.example.demo.constant.dto.ApiResponse;
import com.example.demo.constant.enums.CustomResponseCode;
import com.example.demo.search.dto.SearchDto;
import com.example.demo.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SearchController {

    private final SearchService searchService;
    @PostMapping("/user/search")
    public ResponseEntity<ApiResponse<SearchDto.SearchResponseDto>> search(@RequestBody SearchDto.SearchRequestDto searchRequestDto){
        SearchDto.SearchResponseDto searchResponseDto = searchService.search(searchRequestDto);
        return ResponseEntity.ok().body(ApiResponse.createSuccess(searchResponseDto, CustomResponseCode.SUCCESS));
    }

    @GetMapping("/user/search/relative/{word}")
    public ResponseEntity<ApiResponse<List<String>>> searchWord(@PathVariable String word){
        log.info(word);
        List<String> words = searchService.searchWord(word);
        return ResponseEntity.ok().body(ApiResponse.createSuccess(words,CustomResponseCode.SUCCESS));
    }

    @GetMapping("/user/search/near/mylocation")
    public ResponseEntity<ApiResponse<SearchDto.SearchResponseDto>> searchByMyLocation(
            @RequestParam("x") Double longtitude,
            @RequestParam("y") Double latitude,
            Authentication authentication
    ){
        SearchDto.SearchResponseDto nearCafesMyLocation = searchService.searchByMyLocation(longtitude,latitude);
        return ResponseEntity.ok().body(ApiResponse.createSuccess(nearCafesMyLocation, CustomResponseCode.SUCCESS));
    }
}
