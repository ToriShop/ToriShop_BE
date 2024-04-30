package com.torishop.authentication;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends GenericFilter {
    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        //토큰 가져와
        String Jwt = jwtUtil.getJwt((HttpServletRequest) request);
        //유효성 체크
        if(Jwt != null && jwtUtil.validateJwt(Jwt)){
            //권한 정보가 담긴 토큰(UsernamePasswordAuthenticationToken)을 가져오고
            Authentication authentication = jwtUtil.getAuthFromJwt(Jwt);
            //그 토큰 정보를 security context에 담아준다!!!!!!!!!!!!!!!!!!!
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        //front에서 넘어온 ServletRequest, ServletResponse 객체를 그 다음 필터로 넘겨줌~~
        chain.doFilter(request, response);
    }
}
