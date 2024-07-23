package com.studycow.config.jwt;

import com.studycow.dto.user.CustomUserInfoDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    private Key key;
    private final long accessTokenExpTime;

    public JwtUtil(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.expiration_time}") String accessTokenExpTime
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExpTime = Long.parseLong(accessTokenExpTime);
    }


    public String createAccessToken(CustomUserInfoDto user){
        return createToken(user, accessTokenExpTime);
    }

    private String createToken(CustomUserInfoDto user, long accessTokenExpTime){
        Claims claims = Jwts.claims();
        claims.put("userId",user.getUserId());
        claims.put("userName",user.getUserName());
        claims.put("userEmail",user.getUserEmail());

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(accessTokenExpTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(tokenValidity.toInstant()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    public Long getUserId(String token){
        return parseClaims(token).get("userId", Long.class);
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        }
        catch(io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
            log.info("유효하지 않은 Jwt 토큰입니다.");
        }catch(ExpiredJwtException e){
            log.info("만료된 Jwt 토큰입니다.");
        }catch(UnsupportedJwtException e){
            log.info("지원하지 않는 형식의 토큰값 입니다.");
        }catch(IllegalArgumentException e){
            log.info("Jwt 속성이 비어있습니다.");
        }
        return false;
    }

    public Claims parseClaims(String accessToken){
        try{
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        }catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }
}
