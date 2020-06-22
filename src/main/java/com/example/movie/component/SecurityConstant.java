package com.example.movie.component;

/**
 * @Description: Security 常量
 * @Author: Ou
 * @Date: 2020/6/22
 */
public class SecurityConstant {

    /**
     * MD5秘钥
     */
    public static final String SALT = "necp";

    /**
     * JWT秘钥
     */
    public static final String SIGNING_KEY = "necpJwt";

    /**
     * 授权码
     */
    public static final String KEY_HEAD_TOKEN = "authorization";

    /**
     * 授权码（token）失效时间 （30天）
     */
    public static final long TOKEN_EXPIRATION_TIME = 60 * 60 * 24 * 1000 * 30L;
}
