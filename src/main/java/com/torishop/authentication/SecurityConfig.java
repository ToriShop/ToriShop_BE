package com.torishop.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
        // token을 사용하는 방식이기 때문에 csrf를 disable
        security.csrf((auth) -> auth.disable());
        //세션 관리 상태 없음으로 구성, Spring Security가 세션 생성 or 사용 X
        security.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS));

        //FormLogin, BasicHttp 비활성화
        security.formLogin((form) -> form.disable());
        security.httpBasic(AbstractHttpConfigurer::disable);
        //일반적인 formLogin이 통과하는 필터를 대신해서 들어간다.
        security.addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        //권한 규칙 작성
        security.authorizeHttpRequests(authorize -> authorize
                // /user/login`은 모든 사용자에게 허용
//                .requestMatchers("/user/login").permitAll()
                // `admin` 역할을 가진 사용자에게만 `/product` 경로를 허용
//                .requestMatchers("/product").hasRole("ADMIN")
                // `user` 역할을 가진 사용자에게만 `/cart` 경로를 허용
//                .requestMatchers("/cart").hasRole("USER")
                // 모든 다른 요청은 인증이 필요 -> 계정 있는 사람만 접근 가능
//                .anyRequest().authenticated()
                 .anyRequest().permitAll()
        );

        return security.build();
    }

}
