package com.example.demo.constant.handler;

import com.example.demo.constant.enums.CustomResponseCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String exception = (String) request.getHeader("exception");
        if(exception == null){
            response.sendRedirect("/exception/entrypoint/nullToken");
            return;
        }

        if(exception.equals(CustomResponseCode.EXPIRED_JWT.getMessage())){
            response.sendRedirect("/exception/entrypoint/expiredToken");
            return;
        }

        if(exception.equals(CustomResponseCode.BAD_JWT.getMessage())){
            response.sendRedirect("/exception/entrypoint/badToken");
            return;
        }
    }
}
