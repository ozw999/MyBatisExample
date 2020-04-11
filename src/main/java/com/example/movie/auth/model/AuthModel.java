package com.example.movie.auth.model;

import lombok.Data;

/**
 * @Description:
 * @Author: Ou
 * @Date: 2020/4/1
 */
@Data
public class AuthModel {
    private Integer authId;
    private Integer userId;
    private String token;
    private Boolean deleted;
}
