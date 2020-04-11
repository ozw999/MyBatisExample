package com.example.movie.auth.service;

import com.example.movie.auth.mapper.AuthMapper;
import com.example.movie.auth.model.AuthModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Ou
 * @Date: 2020/4/1
 */
@Service
public class AuthService {

    @Autowired
    private AuthMapper authMapper;

    public AuthModel findById(Integer id){
        return authMapper.findById(id);
    }
}
