package com.example.movie.auth.mapper;

import com.example.movie.auth.model.AuthModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Ou
 * @Date: 2020/4/1
 */
@Mapper
@Component
public interface AuthMapper {
    @Select("select * from auth where auth_id=#{id}")
    @Result(property = "userId",column = "user_id")
    @Result(property = "authId",column = "auth_id")
    AuthModel findById(Integer id);


    @Insert("insert into auth (user_id,token) values (#{userId},#{token})")
    void addToken(Integer userId,String token);

    @Select("select * from auth where token=#{token}")
    @Result(property = "userId",column = "user_id")
    @Result(property = "authId",column = "auth_id")
    AuthModel findByToken(String token);
}
