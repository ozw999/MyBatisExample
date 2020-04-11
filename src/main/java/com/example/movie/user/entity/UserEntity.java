package com.example.movie.user.entity;

import lombok.Data;

/**
 * @Description:
 * @Author: Ou
 * @Date: 2020/3/31
 */
@Data
public class UserEntity {
    private Integer userId;
    private String userName;
    private String passwd;
    private Boolean deleted;
}
