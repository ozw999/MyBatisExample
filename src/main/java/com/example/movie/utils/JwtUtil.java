package com.example.movie.utils;

import com.example.movie.component.SecurityConstant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

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
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstant.TOKEN_EXPIRATION_TIME))
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
    public static String parseToken(String jwt) throws MalformedJwtException, SignatureException {
        String user = Jwts.parser()
                .setSigningKey(SecurityConstant.SIGNING_KEY)
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
        return user;
    }
}
