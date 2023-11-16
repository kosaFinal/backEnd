package com.example.demo.cafe.service.impl;


import com.example.demo.cafe.dto.CafeDto;
import com.example.demo.cafe.entity.Cafe;
import com.example.demo.cafeFeature.mapper.CafeFeatureMapper;
import com.example.demo.cafe.mapper.CafeImgMapper;
import com.example.demo.cafe.mapper.CafeMapper;
import com.example.demo.cafe.service.CafeService;
import com.example.demo.cafeFeature.entity.CafeFeature;
import com.example.demo.userss.entity.Users;
import com.example.demo.userss.mapper.UsersMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CafeServiceImpl implements CafeService {


    private final CafeMapper cafeMapper;
    private final UsersMapper usersMapper;
    private final CafeImgMapper cafeImgMapper;
    private final CafeFeatureMapper cafeFeatureMapper;

    @Override
    public int findUserIdByCafeId(int userId) {
        return cafeMapper.findUserIdByCafeId(userId);
    }
    public String findCafeNameByCafeId(int cafeId){
        return cafeMapper.findCafeNameByCafeId(cafeId);
    }

    @Override
    @Transactional
    public void registerCafe(CafeDto.CafeRegisterRequestDto requestDto, String userName) {

        Users user = usersMapper.getOneUsers(userName);
        log.info(String.valueOf(user));
        requestDto.setUserId(user.getUserId());
        log.info(String.valueOf(user.getUserId()));

        cafeMapper.insertCafe(requestDto);
//        int cafeId = findUserIdByCafeId(user.getUserId());

//        log.info(String.valueOf(cafeId));
//        return CafeDto.CafeRegisterResponseDto.builder()
//                .cafeId(requestDto.getCafeId())
//                .cafeName(requestDto.getCafeName())
//                .userId(requestDto.getUserId())
//                .studyImg(requestDto.getStudyImg())
//                .build();
    }

    @Override
    public CafeDto.CafeSearchDetailResponseDto searchCafeDetail(int cafeId) {
        Cafe cafe = cafeMapper.getOneCafe(cafeId);
        List<CafeFeature> features = cafeFeatureMapper.readCafeFeatures(cafeId);        return null;
    }

}
