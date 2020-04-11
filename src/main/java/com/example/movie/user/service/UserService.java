package com.example.movie.user.service;

import com.example.movie.user.dao.UserMapper;
import com.example.movie.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: Ou
 * @Date: 2020/3/31
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<UserEntity> findUserById(String id){
        List<UserEntity> userById = userMapper.findUserById(id);
        return userById;
    }
}
