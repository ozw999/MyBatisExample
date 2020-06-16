package com.example.movie.login.role.service;

import com.example.movie.login.role.dao.RoleMapper;
import com.example.movie.login.role.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: Ou
 * @Date: 2020/6/16
 */
@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public List<RoleEntity> findByIds(List ids){
        return roleMapper.findByIds(ids);
    }

    public List<RoleEntity> findByUserId(Integer id){
        return roleMapper.findByUserId(id);
    }
}
