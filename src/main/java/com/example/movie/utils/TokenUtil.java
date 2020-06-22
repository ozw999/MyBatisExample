package com.example.movie.utils;

import com.example.movie.component.SecurityConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 授权操作工具
 * @Author: Ou
 * @Date: 2020/6/22
 */
public class TokenUtil {
    public static String getToken(HttpServletRequest request){
        return request.getHeader(SecurityConstant.KEY_HEAD_TOKEN);
    }

    public static void setToken(HttpServletResponse response, String token){
        response.setHeader(SecurityConstant.KEY_HEAD_TOKEN, token);
    }
}
