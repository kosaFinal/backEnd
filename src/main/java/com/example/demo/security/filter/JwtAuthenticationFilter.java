package com.example.demo.security.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import com.example.demo.security.service.JwtUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String accessToken = null;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        if(authorizationHeader != null && authorizationHeader.contains("Bearer")){
            accessToken = authorizationHeader.substring(7);
        }
        else{
            accessToken = request.getParameter("accessToken");
        }
        if(accessToken != null && !accessToken.trim().isEmpty()){
            if(JwtUtils.validateToken(accessToken)){
                //인증객체 생성
                Authentication authentication = jwtUtils.getAuthentication(accessToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request,response);
    }
}
