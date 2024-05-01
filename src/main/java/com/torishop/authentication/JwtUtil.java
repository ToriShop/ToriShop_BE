package com.torishop.authentication;

import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final UserDetailsService userDetailsService;

    @Value("${jwt.secretKey}")
    private String secretKey;
    @PostConstruct // 의존성 주입이 끝난 후 초기화를 수행!! secretKey를 암호화
    protected void init(){secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //jwt 토큰 생성
    public String createJwt(String username, List<String> roleList, int acId){
        //claim: jwt payload에 저장되는 정보단위
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roleList);
        claims.put("acId", acId);

        Instant now = Instant.now();
        return Jwts.builder()
                //getBody()에 대한 부분들
                .setClaims(claims)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .signWith(SignatureAlgorithm.HS256, secretKey) //jwt 생성에 사용할 암호와 알고리즘
                .compact();
    }

    //Request의 header에서 token 값을 가져온다.
    public String getJwt(HttpServletRequest request){
        return request.getHeader("AUTH-TOKEN");
    }

    //토큰의 유효성과 만료 일자를 확인
    //validation 다시 확인하기!!!!!!!!!!!!!!!!!!!!!!!!!!
    public boolean validateJwt(String jwtToken){
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return claims.getBody().getExpiration().after(new Date());
        } catch (Exception e){
            return false;
        }
    }

    //JWT로부터 인증 정보 확인
    public Authentication getAuthFromJwt(String jwtToken){
        //jwt를 secretKey 시그니처로 파싱해서 바디에 있는 subject 값(username)을 가져온다.
        String username = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken).getBody().getSubject();

        //userDetailsService 라는 service 에서 username 을 파라미터로 userDetails(dto) 값을 가져오는 거야.
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        //user에 대한 정보, user가 갖는 역할들을 토큰으로 반환! userDetails에 역할 속성도 있고 그걸 get 할 수 있어.
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    //JWT로부터 admin or customer Id 추출.
    public int getAdminCustomerId(String jwtToken){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken).getBody().get("acId",Integer.class);
    }
}
