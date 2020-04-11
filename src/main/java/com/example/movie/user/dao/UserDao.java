package com.example.movie.user.dao;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

/**
 * @Description:
 * @Author: Ou
 * @Date: 2020/3/31
 */
public class UserDao {
    public String findUserById(String id){
        SQL sql = new SQL();
        sql.SELECT("user_id as userId,user_name as userName,passwd,deleted");
        sql.FROM("user");
        if (!StringUtils.isEmpty(id)){
            sql.WHERE("user_id=#{id}");
        }
        return sql.toString();
    }
}
