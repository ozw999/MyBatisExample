package com.example.movie.config;

import com.example.movie.login.role.dao.RoleMapper;
import com.example.movie.login.role.entity.RoleEntity;
import com.example.movie.user.dao.UserMapper;
import com.example.movie.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: Ou
 * @Date: 2020/6/16
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userMapper.findUserByName(username);
        List<RoleEntity> roleEntity = roleMapper.findByUserId(userEntity.getUserId());
        userEntity.setRoles(roleEntity);
        return userEntity;
    }
}
