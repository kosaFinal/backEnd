package com.example.demo.cafe.service.impl;

import com.example.demo.cafe.dto.CafeImgDto;
import com.example.demo.cafe.mapper.CafeImgMapper;
import com.example.demo.cafe.mapper.CafeMapper;
import com.example.demo.cafe.service.CafeImgService;
import com.example.demo.userss.mapper.UsersMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CafeImgServiceImpl implements CafeImgService{

    private final CafeImgMapper cafeImgMapper;
    private final UsersMapper usersMapper;



    public int findCafeIdByUserName(String userName){
        return cafeImgMapper.findCafeIdByUserName(userName);
    }

    @Override
    public void insertCafeImg(List<CafeImgDto.CafeRegisterImgRequestDto> cafeImgDtos, String userName) {
        int cafeId = cafeImgMapper.findCafeIdByUserName(userName);

        for (CafeImgDto.CafeRegisterImgRequestDto imgDto : cafeImgDtos) {
            imgDto.setCafeId(cafeId);
            cafeImgMapper.insertCafeImg(imgDto);
        }


    }

}
