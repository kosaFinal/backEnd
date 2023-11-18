package com.example.demo.cafe.service.impl;

import com.example.demo.cafe.dto.CafeUpdateDto;
import com.example.demo.cafe.entity.Cafe;
import com.example.demo.cafe.mapper.CafeImgMapper;
import com.example.demo.cafe.mapper.CafeMapper;
import com.example.demo.cafe.mapper.CafeUpdateMapper;
import com.example.demo.cafe.service.CafeUpdateService;
import com.example.demo.constant.dto.ApiResponse;
import com.example.demo.constant.enums.CustomResponseCode;
import com.example.demo.constant.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CafeUpdateServiceImpl implements CafeUpdateService {

    private final CafeUpdateMapper cafeUpdateMapper;
    private final CafeImgMapper cafeImgMapper;
    private final CafeMapper cafeMapper;

    private void validateCafeTime(CafeUpdateDto.CafeTimeRequestDto cafeTime) throws GeneralException {
        try {
            LocalTime startTime = LocalTime.parse(cafeTime.getStartTime());
            LocalTime endTime = LocalTime.parse(cafeTime.getEndTime());

            if (!startTime.isBefore(endTime)) {
                throw new GeneralException(CustomResponseCode.CAFE_OPERATOR_TIME_OVER);
            } else if (startTime.equals(endTime)) {
                throw new GeneralException(CustomResponseCode.CAFE_OPERATOR_TIME_EQUALS);
            }
        } catch (DateTimeParseException e) {
            throw new GeneralException(CustomResponseCode.CAFE_OPERATOR_TIME_MISMATCH);
        }
    }


    @Override
    public CafeUpdateDto.CafeTelResponseDto cafeTelUpdate(CafeUpdateDto.CafeTelRequestDto cafeTel, String userName) {
        int cafeId = cafeImgMapper.findCafeIdByUserName(userName);
        cafeUpdateMapper.updateCafeTel(cafeTel.getCafeTel(), cafeId);

        Cafe cafe = cafeMapper.getOneCafe(cafeId);
        return new CafeUpdateDto.CafeTelResponseDto(cafe);
    }

    @Override
    public CafeUpdateDto.CafeLocationResponseDto cafeAddressUpdate(CafeUpdateDto.CafeLocationRequestDto cafeAddress, String userName) {
        int cafeId = cafeImgMapper.findCafeIdByUserName(userName);
        cafeUpdateMapper.updateCafeAddress(cafeAddress.getAddress(), cafeAddress.getLongtitude(), cafeAddress.getLatitude(), cafeId);

        Cafe cafe = cafeMapper.getOneCafe(cafeId);
        return new CafeUpdateDto.CafeLocationResponseDto(cafe);
    }

    @Override
    public CafeUpdateDto.CafeTimeResponseDto cafeTimeUpdate(CafeUpdateDto.CafeTimeRequestDto cafeTime, String userName) {
        validateCafeTime(cafeTime);
        int cafeId = cafeImgMapper.findCafeIdByUserName(userName);

        cafeUpdateMapper.updateCafeTime(cafeTime.getStartTime(), cafeTime.getEndTime(), cafeId);
        Cafe cafe = cafeMapper.getOneCafe(cafeId);
        return new CafeUpdateDto.CafeTimeResponseDto(cafe);
    }

    @Override
    public CafeUpdateDto.CafeStudyResponseDto cafeStudyUpdate(CafeUpdateDto.CafeStudyRequestDto cafeStudy, String userName) {
        int cafeId = cafeImgMapper.findCafeIdByUserName(userName);
        cafeUpdateMapper.updateCafeStudy(cafeStudy.getStudy(), cafeId);
        Cafe cafe = cafeMapper.getOneCafe(cafeId);
        return new CafeUpdateDto.CafeStudyResponseDto(cafe);
    }
}
