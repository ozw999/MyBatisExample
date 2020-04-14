package com.example.movie.play.dao;

import com.example.movie.play.entity.MovieEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: Ou
 * @Date: 2020/4/1
 */
@Mapper
@Component
public interface MovieMapper {

    MovieEntity findByMovieName(String name);

    List<MovieEntity> findByUserIds(List userIds);

    void update(MovieEntity entity);

    void add(MovieEntity entity);

    void delete(Integer movieId);
}
