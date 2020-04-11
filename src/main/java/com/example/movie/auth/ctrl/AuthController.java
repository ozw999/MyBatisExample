package com.example.movie.auth.ctrl;

import com.example.movie.auth.model.AuthModel;
import com.example.movie.auth.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Ou
 * @Date: 2020/3/31
 */
@RestController
@RequestMapping(value = "/auth", method = {RequestMethod.POST})
@Api(value = "授权", tags = "授权操作")
public class AuthController {

    @Autowired
    private AuthService authService;

    @ApiOperation("获取授权")
    @RequestMapping(value = "/findAuthById")
    public AuthModel findAuthById(Integer id) {
        return authService.findById(id);
    }

}
