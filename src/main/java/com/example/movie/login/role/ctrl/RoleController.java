package com.example.movie.login.role.ctrl;

import com.example.movie.login.role.entity.RoleEntity;
import com.example.movie.login.role.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: Ou
 * @Date: 2020/6/16
 */
@RestController
@RequestMapping(value = "/role",method = RequestMethod.POST)
@Api(tags = "角色操作")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/getRoleByRoleIds")
    @ApiOperation(value = "按照Ids获取角色")
    public List<RoleEntity> getRoleByRoleIds(String ids){
        List<String> list = Arrays.asList(ids.split(","));
        List<Integer> integerList = list.stream().map(s -> Integer.valueOf(s)).collect(Collectors.toList());
        return roleService.findByIds(integerList);
    }
}
