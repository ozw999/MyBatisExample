package com.example.movie.config;

import com.example.movie.auth.service.AuthService;
import com.example.movie.component.CommonResponse;
import com.example.movie.component.LoginFailureEnum;
import com.example.movie.utils.JwtUtil;
import com.example.movie.utils.SpringUtil;
import com.example.movie.utils.ResponseUtil;
import com.example.movie.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @Description:
 * @Author: Ou
 * @Date: 2020/6/23
 */
public class AuthenticationFilter extends BasicAuthenticationFilter {

    private AuthService authService;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        authService = SpringUtil.getBean(AuthService.class);
    }

    /**
     * 过滤拦截
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!authorize(request, response)) {
            return;
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (null != auth && !auth.isAuthenticated()) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), auth.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }

    /**
     * 授权验证
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public boolean authorize(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String token = TokenUtil.getToken(request);
            Date authDate = JwtUtil.parseToken(token).getExpiration();
            if (null == token) {
                response.setStatus(400);
                CommonResponse result = new CommonResponse(CommonResponse.FAILURE_CODE, "token非法或不存在");
                ResponseUtil.writeData(response,result);
                return false;
            }
            if (null == authService.findByToken(token)){
                response.setStatus(400);
                CommonResponse result = new CommonResponse(CommonResponse.FAILURE_CODE, "token非法或不存在");
                ResponseUtil.writeData(response,result);
                return false;
            }
            if (authDate.before(new Date(System.currentTimeMillis()))){
                response.setStatus(400);
                CommonResponse result = new CommonResponse(CommonResponse.FAILURE_CODE, "token已过期");
                ResponseUtil.writeData(response,result);
                return false;
            }
        } catch (IOException e) {
            response.setStatus(400);
            CommonResponse result = new CommonResponse(CommonResponse.FAILURE_CODE, e.getMessage());
            ResponseUtil.writeData(response,result);
            return false;
        }
        return true;
    }
}
