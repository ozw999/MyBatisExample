package com.example.movie.config;

import com.example.movie.utils.MD5Util;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description:MD5 加密
 * @Author: Ou
 * @Date: 2020/6/10
 */
public class MD5PasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        //todo 为何使用CharSequence
        return MD5Util.encode((String) rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5Util.encode((String)rawPassword));
    }

}
