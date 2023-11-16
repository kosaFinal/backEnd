package com.example.demo.cafe.service.impl;

import com.example.demo.cafe.dto.CafeDto;
import com.example.demo.cafe.mapper.CafeImgMapper;
import com.example.demo.cafe.mapper.CafeMapper;
import com.example.demo.cafe.service.CafeService;
import com.example.demo.userss.entity.Users;
import com.example.demo.userss.mapper.UsersMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class CafeServiceImpl implements CafeService {

    private final CafeMapper cafeMapper;
    private final UsersMapper usersMapper;
    private final CafeImgMapper cafeImgMapper;


    private String extractMimeType(byte[] imageData) throws IOException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(imageData)) {
            ImageInputStream iis = ImageIO.createImageInputStream(bais);
            Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
            if (!iter.hasNext()) {
                throw new RuntimeException("Unable to determine image type");
            }
            ImageReader reader = iter.next();
            return "image/" + reader.getFormatName().toLowerCase();
        }
    }


    @Override
    public int findUserIdByCafeId(int userId) {
        return cafeMapper.findUserIdByCafeId(userId);
    }

    public String findCafeNameByCafeId(int cafeId) {
        return cafeMapper.findCafeNameByCafeId(cafeId);
    }

    @Override
    @Transactional
    public void registerCafe(CafeDto.CafeRegisterRequestDto requestDto, String userName, MultipartFile cafeRepImgFile, MultipartFile studyImgFile) {
        Users user = usersMapper.getOneUsers(userName);
        requestDto.setUserId(user.getUserId());

        try {
            // 대표 이미지 처리
            if (cafeRepImgFile != null && !cafeRepImgFile.isEmpty()) {
                byte[] repImgBytes = cafeRepImgFile.getBytes();
                requestDto.setCafeRepImg(repImgBytes);
                String mimeType = extractMimeType(repImgBytes);
                if (mimeType != null) {
                    requestDto.setCafeRepImgMine(mimeType);
                }
            }

            // 스터디 이미지 처리
            if (studyImgFile != null && !studyImgFile.isEmpty()) {
                byte[] studyImgBytes = studyImgFile.getBytes();
                requestDto.setStudyImg(studyImgBytes);
                String mimeType = extractMimeType(studyImgBytes);
                if (mimeType != null) {
                    requestDto.setStudyImgMine(mimeType);
                }
            }

            cafeMapper.insertCafe(requestDto);
        } catch (IOException e) {
            log.error("Error occurred while processing images", e);
            // Handle the exception according to your business logic
        }
    }
}