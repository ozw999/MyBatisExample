package com.example.movie.auth.ctrl;

import com.example.movie.auth.model.AuthModel;
import com.example.movie.auth.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 授权操作
 * @Author: Ou
 * @Date: 2020/3/31
 */
@RestController
@RequestMapping(value = "/auth", method = {RequestMethod.POST})
@Api(value = "授权", tags = "授权操作")
public class AuthController {

    //自动装配，默认使用byType来装配属性，如果匹配到类型的多个实例，再通过byName来确定Bean
    @Autowired
    private AuthService authService;

    @ApiOperation("根据ID获取授权")
    @RequestMapping(value = "/findAuthById")
    public AuthModel findAuthById(@ApiParam(value = "ID", required = true) @RequestParam Integer id) {
        return authService.findById(id);
    }

    @ApiOperation("添加授权")
    @RequestMapping(value = "/addAuth")
    public void addAuth(@ApiParam(value = "用户ID", required = true) @RequestParam Integer userId,
                        @ApiParam(value = "授权码", required = true) @RequestParam String token) {
        authService.addToken(userId, token);
    }

    @ApiOperation("根据token获取授权")
    @RequestMapping(value = "/findAuthByToken")
    public AuthModel Auth(@ApiParam(value = "token", required = true) @RequestParam String token) {
        return authService.findByToken(token);
    }

}
