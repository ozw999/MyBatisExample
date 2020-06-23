package com.example.movie.config;

import com.example.movie.auth.service.AuthService;
import com.example.movie.component.CommonResponse;
import com.example.movie.utils.SpringUtil;
import com.example.movie.user.entity.UserEntity;
import com.example.movie.utils.JwtUtil;
import com.example.movie.utils.ResponseUtil;
import com.example.movie.utils.TokenUtil;
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

    //非容器类无法通过Autowired注入bean
//    @Autowired
//    private AuthService authService;

    private AuthService authService;

    public CustomAuthSuccessHandler(){
        authService = SpringUtil.getBean(AuthService.class);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        UserEntity entity = (UserEntity) authentication.getPrincipal();
        String username = entity.getUsername();
        String token = JwtUtil.createToken(username);
        TokenUtil.setToken(httpServletResponse, token);
        CommonResponse result = new CommonResponse(CommonResponse.SUCCESS_CODE, "登录成功");
        ResponseUtil.writeData(httpServletResponse, result);
        //将token信息存入数据表
        authService.addToken(entity.getUserId(),token);
    }
}
