package com.example.movie.utils;

import com.example.movie.component.SecurityConstant;
import io.jsonwebtoken.*;

import java.util.Date;

/**
 * @Description: JWT：json web token, eg. Header.Payload.Signature 工具类
 * @Author: Ou
 * @Date: 2020/6/22
 */
public class JwtUtil {

    /**
     * 生成jwt
     * @param username
     * @return
     */
    public static String createToken(String username) {
        if(null == username){
            throw new RuntimeException("username must be not null!");
        }
        String token = Jwts.builder()
                //主体
                .setSubject(username)
                //过期时间
//                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstant.TOKEN_EXPIRATION_TIME))
                .setExpiration(new Date(System.currentTimeMillis()))
                //签名算法及密匙
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.SIGNING_KEY)
                .compact();
        return token;
    }

    /**
     * 解密jwt
     * @param jwt
     * @return
     * @throws MalformedJwtException
     * @throws SignatureException
     */
    public static Claims parseToken(String jwt) throws MalformedJwtException, SignatureException,ExpiredJwtException {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SecurityConstant.SIGNING_KEY)
                    .parseClaimsJws(jwt)
                    .getBody();
            return claims;
        }catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }

    public static void main(String[] args) {
        String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU5NTQ5NDQyMX0.aZpcOhXL-4j8kWgM0H6orWaWfpveqdJqk9hNXIvZpFDq9EQ48Ar1d-xYeQYCIhv7w8ukhbcH4PYUmBN5zps2tA";
        Date authDate = Jwts.parser().setSigningKey(SecurityConstant.SIGNING_KEY).parseClaimsJws(jwt).getBody().getExpiration();
        System.out.println(authDate.after(new Date(System.currentTimeMillis())));
    }
}
