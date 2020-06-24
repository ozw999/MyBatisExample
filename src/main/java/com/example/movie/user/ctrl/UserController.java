package com.example.movie.user.ctrl;

import com.example.movie.user.entity.UserEntity;
import com.example.movie.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: Ou
 * @Date: 2020/3/31
 */
@RestController
@RequestMapping(value = "/user", method = {RequestMethod.POST,RequestMethod.GET})
@Api(value = "用户", tags = "用户操作")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("通过ID获取用户")
    @RequestMapping(value = "/findUserById")
    public List<UserEntity> findUserById(@ApiParam(value = "用户ID",required = true) @RequestParam String id) {
        return userService.findUserById(id);
    }

    @ApiOperation("通过用户名获取用户")
    @RequestMapping(value = "/findUserByName")
    public UserEntity findUserByName(@ApiParam(value = "用户名",required = true) @RequestParam String username) {
        return userService.findUserByName(username);
    }

}
