package com.example.movie.login.role.dao;

import com.example.movie.login.role.entity.RoleEntity;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: Ou
 * @Date: 2020/6/16
 */
@Mapper
@Component
public interface RoleMapper {
   List<RoleEntity> findByIds(List ids);

   List<RoleEntity> findByUserId(Integer id);
}
