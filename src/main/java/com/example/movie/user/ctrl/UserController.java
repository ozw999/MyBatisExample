package com.example.movie.user.ctrl;

import com.example.movie.user.entity.UserEntity;
import com.example.movie.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: Ou
 * @Date: 2020/3/31
 */
@RestController
@RequestMapping(value = "/user", method = {RequestMethod.POST})
@Api(value = "用户", tags = "用户操作")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("获取用户")
    @RequestMapping(value = "/findUserById")
    public List<UserEntity> findUserById(String id) {
        return userService.findUserById(id);
    }

}
