package com.example.demo.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.demo.security.filter.JwtAuthenticationFilter;

@Configuration
@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Authorization 헤더를 통한 인증 사용하지 않음
        http.httpBasic(config -> config.disable());

        // 폼을 통한 인증 사용하지 않음
        http.formLogin(config -> config.disable());

        // CORS 설정
        http.cors(config -> {
        });

        // 사이트간 요청 위조 방지 비활성화
        http.csrf(config -> config.disable());

        // 서버 세션 비활성화 -> JWT 사용할 때만
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 요청 경로별 권한 설정
        http.authorizeHttpRequests(customizer -> customizer
                // 방법1
//                .antMatchers("/board/**").hasAuthority("ROLE_USER")

                // 방법2
                // .antMatchers(HttpMethod.GET, "/board/list").hasAuthority("ROLE_USER")
                // //ROLE_생략하면 안됨
                // .antMatchers(HttpMethod.POST, "/board/create").hasAnyRole("USER") //ROLE_ 붙이면
                // 안됨
                // .antMatchers(HttpMethod.GET, "/board/read/*").hasAnyRole("USER")
                // .antMatchers(HttpMethod.PUT, "/board/update").hasAnyRole("USER")
                // .antMatchers(HttpMethod.DELETE, "/board/delete/*").hasAnyRole("USER")
                // .antMatchers(HttpMethod.POST, "/board/createWithAttach").hasAnyRole("USER")
                // .antMatchers(HttpMethod.GET, "/board/battach/*").hasAnyRole("USER")

                // 그 이외의 모든 경로 허가
                .anyRequest().permitAll());

        // Jwt token 필터 추가
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

//        return http
//                .httpBasic(AbstractHttpConfigurer::disable)
//                .formLogin(AbstractHttpConfigurer::disable)
//                .cors(config->{})
//                .csrf(config->config.disable())
//                .sessionManagement(
//                        management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .authorizeHttpRequests(
//                        request -> request
//                                .antMatchers("/user/*").hasAuthority("ROLE_USER")
//                                .antMatchers(("/manager/*")).hasRole("MANAGER")
//                                .anyRequest().permitAll()
//                )
//
//                .build();
    }


   //비빌번호 인코더 설정
   @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //인증 관리자 설정
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
