package com.example.movie.login.role.entity;

import lombok.Data;

/**
 * @Description:角色实体类
 * @Author: Ou
 * @Date: 2020/6/16
 */
@Data
public class RoleEntity {
    private Integer roleId;
    private String roleName;
    private Boolean deleted;
}
