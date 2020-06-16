package com.example.movie.user.dao;

import com.example.movie.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: Ou
 * @Date: 2020/3/31
 */
@Mapper
@Component
public interface UserMapper {
    @SelectProvider(type = UserDao.class,method = "findUserById")
    List<UserEntity> findUserById(String id);

    @SelectProvider(type = UserDao.class,method = "findUserByName")
    UserEntity findUserByName(String username);
}
