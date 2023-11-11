package com.example.demo.cafeTable.controller;


import com.example.demo.cafeTable.service.CafeTableService;
import com.example.demo.constant.dto.ApiResponse;
import com.example.demo.constant.enums.CustomResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CafeTableController {

    @Autowired
    private CafeTableService cafeTableService;

    // 카페id, 테이블 타입을 전달받아 해당 테이블넘버 불러오기
    @GetMapping ("/user/reservation/table/{cafeId}/{tabletype}")
    public ResponseEntity<ApiResponse<List<String>>> getTableNumbers (@PathVariable int cafeId, @PathVariable String tabletype){
        List<String> tableNumbers = cafeTableService.getTableNumbers(cafeId, tabletype);
        return ResponseEntity.ok().body(ApiResponse.createSuccess(tableNumbers, CustomResponseCode.SUCCESS));
    }

}