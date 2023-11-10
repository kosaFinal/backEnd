package com.example.demo.userss.service.impl;

import com.example.demo.constant.enums.CustomResponseCode;
import com.example.demo.constant.exception.GeneralException;
import com.example.demo.security.service.JwtUtils;
import com.example.demo.userss.dto.UsersDto;
import com.example.demo.userss.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UsersService {

    private final AuthenticationManager authenticationManager;
    @Override
    public UsersDto.UserLoginResponseDto login(UsersDto.UserLoginRequestDto userLoginRequestDto) {
        log.info("service 들어옴");
        //아이디와 비밀번호를 가지고 있는 객체 생성
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userLoginRequestDto.getUserName(),userLoginRequestDto.getPassword());
        log.info(authToken.toString());
        //DB에서 사용자 정보와 일치하는지 검사해서
        //맞을 경우 authentication 객체 return
        //아닐경우 403에러
        Authentication authentication = authenticationManager.authenticate(authToken);
        if(authentication == null ){
            throw new GeneralException(CustomResponseCode.NO_AUTHENTICATION);
        }

        //security에 인증객체 등록
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = JwtUtils.createToken(userLoginRequestDto.getUserName());
        log.info(accessToken);
        return UsersDto.UserLoginResponseDto.builder()
                .userName(userLoginRequestDto.getUserName())
                .accessToken(accessToken)
                .build();
    }
}
