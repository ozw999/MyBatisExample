package com.example.movie.play.dao;

import com.example.movie.play.entity.MovieEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Ou
 * @Date: 2020/4/1
 */
@Mapper
@Component
public interface MovieMapper {
    MovieEntity findByMovieName(String name);
}
