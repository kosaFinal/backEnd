package com.example.demo.security.service;

import com.example.demo.constant.enums.CustomResponseCode;
import com.example.demo.constant.exception.GeneralException;
import com.example.demo.userss.mapper.UsersMapper;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtUtils {

    private final UsersMapper usersMapper;
    //비밀키(누출되면 안됨)
    private static final String secretKey = "secret";

    // JWT 토큰 생성: 사용자 아이디 포함
    public static String createToken(String userName) {
        String token = null;
        JwtBuilder builder = Jwts.builder();
        builder.setHeaderParam("typ", "JWT"); //토큰의 종류
        builder.setHeaderParam("alg", "HS256"); //암호화 알고리즘 종류
        builder.setExpiration(new Date(new Date().getTime() + 1000*60*60*12)); //토큰의 유효기간
        builder.claim("userName", userName); //토큰에 저장되는 데이터
        builder.signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8)); //비밀키
        token = builder.compact(); //모든 내용을 묶기
        return token;
    }

    //JWT 토큰에서 모든 내용(Claims) 얻기
    public static Claims getClaims(String token) {
        Claims claims = null;
        try {
            JwtParser parser = Jwts.parser();
            parser.setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8));
            Jws<Claims> jws = parser.parseClaimsJws(token);
            claims = jws.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    //JWT 토큰에서 사용자 이름 얻기
    public static String getUserName(String token) {
        String userName = null;
        try {
            JwtParser parser = Jwts.parser();
            parser.setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8));
            Jws<Claims> jws = parser.parseClaimsJws(token);
            Claims claims = jws.getBody();
            userName = claims.get("userName", String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userName;
    }

    //JWT 토큰 유효성 검사: 만료일자 확인 true - 유효,
    public static boolean validateToken(String token) {
        boolean validate = false;
        try {
            JwtParser parser = Jwts.parser();
            parser.setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8));
            Jws<Claims> jws = parser.parseClaimsJws(token);
            Claims claims = jws.getBody();
            validate = !claims.getExpiration().before(new Date());
         /*if(validate) {
            long remainMillSecs = claims.getExpiration().getTime() - new Date().getTime();
            logger.info("" + remainMillSecs/1000 + "초 남았음");
         }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return validate;
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = usersMapper.getOneUsers(getUserName(token));
        if(userDetails == null){
            throw new GeneralException(CustomResponseCode.USER_NOT_FOUND);
        }
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }

}
