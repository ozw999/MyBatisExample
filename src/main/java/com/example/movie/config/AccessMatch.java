package com.example.movie.config;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 判断是否无需通过认证
 * @Author: Ou
 * @Date: 2020/6/24
 */
public interface AccessMatch {

    /**
     * 是否允许通过
     * @param request
     * @return
     */
    boolean isAccessUri(HttpServletRequest request);
}
