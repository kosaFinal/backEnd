package com.example.demo.search.controller;

import com.example.demo.constant.dto.ApiResponse;
import com.example.demo.constant.enums.CustomResponseCode;
import com.example.demo.search.dto.SearchDto;
import com.example.demo.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;
    @PostMapping("/user/search")
    public ResponseEntity<ApiResponse<SearchDto.SearchResponseDto>> search(@RequestBody SearchDto.SearchRequestDto searchRequestDto){
        SearchDto.SearchResponseDto searchResponseDto = searchService.search(searchRequestDto);
        return ResponseEntity.ok().body(ApiResponse.createSuccess(searchResponseDto, CustomResponseCode.SUCCESS));
    }
}
