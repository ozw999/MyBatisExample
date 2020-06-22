package com.example.movie.config;

import com.example.movie.component.CommonResponse;
import com.example.movie.user.dao.UserMapper;
import com.example.movie.user.entity.UserEntity;
import com.example.movie.utils.JwtUtil;
import com.example.movie.utils.ResponseUtil;
import com.example.movie.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 自定义认证成功回调
 * @Author: Ou
 * @Date: 2020/6/22
 */
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        String username = ((UserEntity) authentication.getPrincipal()).getUsername();
        String token = JwtUtil.createToken(username);
        TokenUtil.setToken(httpServletResponse, token);
        CommonResponse result = new CommonResponse(CommonResponse.SUCCESS_CODE, "登录成功");
        ResponseUtil.writeData(httpServletResponse, result);
    }
}
